package com.epam.task2.model.concurrent;

import com.epam.task2.model.Bank;
import com.epam.task2.model.Account;
import com.epam.task2.model.TransferInfo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class BankWithLock extends Bank {
    private final Lock bankLock = new ReentrantLock();
    private final Condition sufficientFounds = bankLock.newCondition();

    @Override
    public boolean transfer(TransferInfo transferInfo) throws InterruptedException {
        Account sourceAccount = getAccount(transferInfo.getSourceAccountNumber());
        Account targetAccount = getAccount(transferInfo.getTargetAccountNumber());
        if (sourceAccount != null && targetAccount != null) {
            bankLock.lock();
            try {
                while (transferInfo.getAmount().equals(sourceAccount.getValue())) {
                    sufficientFounds.await();
                }
                sourceAccount.setValue(sourceAccount.getValue().add(transferInfo.getAmount().negate()));
                targetAccount.setValue(targetAccount.getValue().add(transferInfo.getAmount()));
                sufficientFounds.signalAll();
                return true;
            } finally {
                bankLock.unlock();
            }
        }
        return false;
    }
}
