package javase.t02.accounts;

import javase.t02.transactions.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class for management of account balances and transactions
 * Created by Yury Vislobodsky on 15.04.2016.
 */
public class AccountsHandler {
    private BlockingQueue<Account> accounts = new LinkedBlockingQueue<>();
    final Logger logger = LoggerFactory.getLogger(AccountsHandler.class);

    public void transactionExecute(Transaction transaction) {
        int accountIdFrom = transaction.getAccountIdFrom();
        int accountIdTo = transaction.getAccountIdTo();
        int sum = transaction.getSum();

        Account accountFrom = extractAccountById(accountIdFrom);
        Account accountTo = extractAccountById(accountIdTo);

        // to prevent a deadlock
        synchronized (minAccount(accountFrom, accountTo)) {
            synchronized (maxAccount(accountFrom, accountTo)) {
                StringBuilder log = new StringBuilder();
                log.append("\nBefore: ").append(accountFrom).append(", ").append(accountTo);
                log.append("\n").append(Thread.currentThread().getName()).append(" : ").append(transaction);

                if (accountFrom.getBalance() >= sum) {
                    accountFrom.offsetBalance(-sum);
                    accountTo.offsetBalance(sum);
                    log.append(" - APROOVED");
                } else {
                    log.append(" - REJECTED");
                }

                log.append("\nAfter:  ").append(accountFrom).append(", ").append(accountTo);
                log.append("\n");
                logger.info(log.toString());
            }
        }
    }

    public void offsetAccountBalance(int accountId, int offsetSum) {
        Account account = extractAccountById(accountId);
        account.offsetBalance(offsetSum);
    }

    private Account extractAccountById(int accountId) {
        Account newAccount = new Account(accountId, 0);
        for (Account account : accounts) {
            if (account.equals(newAccount)) {
                return account;
            }
        }
        accounts.add(newAccount);
        return newAccount;
    }

    private Account minAccount(Account account1, Account account2) {
        return account1.hashCode() < account2.hashCode() ? account1 : account2;
    }

    private Account maxAccount(Account account1, Account account2) {
        return account1.hashCode() > account2.hashCode() ? account1 : account2;
    }

    public int getTotalBalance() {
        int totalBalance = 0; // Sonar requires this initialization
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Account account : accounts) {
            string.append("\n").append(account);
        }
        string.append("\n");
        return string.toString();
    }
}
