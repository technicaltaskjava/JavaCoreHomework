package com.epam.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class BankConcurrent extends Bank {

    private Lock bankLock;
    private Condition sufficientFunds;

    public BankConcurrent(int countAccounts, double initialBalance) {
        super(countAccounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    @Override
    public void transfer(int sender, int recipient, double amount) {
        bankLock.lock();
        try {
            while (accounts.get(sender).getBalance() < amount) {
                try {
                    sufficientFunds.await();
                } catch (InterruptedException e) {
                    log.error(e);
                    Thread.currentThread().interrupt();
                }
            }
            accounts.get(sender).changeBalance(-amount);
            accounts.get(recipient).changeBalance(amount);
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }
}
