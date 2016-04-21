package com.epam.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by o.gondar on 20.04.2016.
 */
public class AccountOperator {

    private Account account1;
    private Account account2;
    private Lock lock;

    public AccountOperator(Account account1, Account account2) {
        this.account1 = account1;
        this.account2 = account2;
        this.lock = new ReentrantLock();
    }

    public void performSynchronizedOperation(String account, int summ) {
        synchronized (account1) {
            synchronized (account2) {
                changeAccountsValues(account, summ);
            }
        }
    }

    public void performConcurrentOperation(String account, int summ) {
        lock.lock();
        changeAccountsValues(account, summ);
        lock.unlock();
    }


    private void changeAccountsValues(String account, int summ) {

        if ("account1".equals(account) && account1.getBalance() >= summ) {
            account1.withdraw(summ);
            account2.deposit(summ);
        } else if ("account2".equals(account) && account2.getBalance() >= summ) {
            account2.withdraw(summ);
            account1.deposit(summ);
        } else if (("account1".equals(account) && account1.getBalance() < summ) || ("account2".equals(account) && account2.getBalance() < summ)) {
            System.out.println("Not enough money! Operation " + account + " deposit " + summ + " not performed!");
        }
    }
}
