package javase.t02.demo;

import javase.t02.accounts.AccountsHandler;
import javase.t02.accounts.AccountsRunnable;
import javase.t02.transactions.Transaction;
import javase.t02.transactions.TransactionsParser;
import javase.t02.transactions.TransactionsParserException;

import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo Class to present accounts handling with transaction execution in threads
 * Created by Yury Vislobodsky on 16.04.2016.
 */
public class AccountsHandlerDemo {
    private static Logger logger = LoggerFactory.getLogger(AccountsHandlerDemo.class);
    private static final int INITIAL_SUM = 50;
    private static final int ACCOUNTS_COUNT = 6;

    private AccountsHandlerDemo() {}

    public static void main(String[] args) throws TransactionsParserException, InterruptedException {
        AccountsHandler accountsHandler = new AccountsHandler();

        // init accounts balance
        for (int accountId = 1; accountId <= ACCOUNTS_COUNT; accountId++) {
            accountsHandler.offsetAccountBalance(accountId, INITIAL_SUM);
        }

        logger.info("\nBeginning balance:" + accountsHandler);

        BlockingQueue<Transaction> transactions =
                TransactionsParser.createFromXml("src/main/resources/transactions.xml");

        int threadsCount = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        int maxTransactionsPerThread = (threadsCount == 0) ? 0 : transactions.size() / threadsCount + 1;
        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            executor.execute(new AccountsRunnable(accountsHandler, transactions, maxTransactionsPerThread));
        }
        executor.shutdown();

        executor.awaitTermination(10, TimeUnit.SECONDS);

        logger.info("\nEnding balance:" + accountsHandler);
    }
}
