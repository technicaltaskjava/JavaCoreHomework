package javase.t02.demo;

import static org.junit.Assert.*;

import javase.t02.accounts.AccountsHandler;
import javase.t02.accounts.AccountsRunnable;
import javase.t02.transactions.Transaction;
import javase.t02.transactions.TransactionsParser;
import javase.t02.transactions.TransactionsParserException;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Test for AccountsHandlerDemo class
 * Based on rule that final total accounts sum wasn't change after execution of all transactions
 * Created by Yury Vislobodsky on 18.04.2016.
 */
public class AccountsHandlerDemoTest {
    AccountsHandler accountsHandler = new AccountsHandler();
    BlockingQueue<Transaction> transactions;
    private static final int INITIAL_SUM = 50;
    private static final int ACCOUNTS_COUNT = 6;

    @Before
    public void setUp() throws TransactionsParserException {
        for (int accountId = 1; accountId <= ACCOUNTS_COUNT; accountId++) {
            accountsHandler.offsetAccountBalance(accountId, INITIAL_SUM);
        }
        transactions = TransactionsParser.createFromXml("src/main/resources/transactions.xml");
    }

    @Test
    public void testAccountHandlerExecute() throws InterruptedException {
        int threadsCount = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        int maxTransactionsPerThread = (threadsCount == 0) ? 0 : transactions.size() / threadsCount + 1;
        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            executor.execute(new AccountsRunnable(accountsHandler, transactions, maxTransactionsPerThread));
        }
        executor.shutdown();

        executor.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals("error in transactions execution", ACCOUNTS_COUNT * INITIAL_SUM, accountsHandler.getTotalBalance());
    }
}