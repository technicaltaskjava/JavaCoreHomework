package javase.t02.accounts;

import javase.t02.transactions.Transaction;
import java.util.concurrent.BlockingQueue;

/**
 * Thread class for transactions execution
 * Created by Yury Vislobodsky on 16.04.2016.
 */
public class AccountsRunnable implements Runnable {
    private AccountsHandler accountsHandler;
    private BlockingQueue<Transaction> transactions;
    private int maxTransactionsCount;

    public AccountsRunnable(AccountsHandler accountsHandler,
                            BlockingQueue<Transaction> transactions, int maxTransactionsCount) {
        this.accountsHandler = accountsHandler;
        this.transactions = transactions;
        this.maxTransactionsCount = maxTransactionsCount;
    }

    @Override
    public void run() {
        int transactionsCount = 0; // Sonar requires this initialization
        do {
            Transaction transaction = transactions.poll();
            if (transaction == null) {
                break;
            }
            accountsHandler.transactionExecute(transaction);
            transactionsCount++;
        } while (transactionsCount <= maxTransactionsCount);
    }
}
