package com.epam.task2.model.sync;

import com.epam.task2.model.Bank;
import com.epam.task2.model.Account;
import com.epam.task2.model.TransferInfo;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class BankSync extends Bank {
    private final Object lock = new Object();

    @Override
    public boolean transfer(TransferInfo transferInfo) throws InterruptedException {
        Account sourceAccount = getAccount(transferInfo.getSourceAccountNumber());
        Account targetAccount = getAccount(transferInfo.getTargetAccountNumber());
        if (sourceAccount != null && targetAccount != null) {
            synchronized (lock) {
                while (transferInfo.getAmount().equals(sourceAccount.getValue())) {
                    lock.wait();
                }
                sourceAccount.setValue(sourceAccount.getValue().add(transferInfo.getAmount().negate()));
                targetAccount.setValue(targetAccount.getValue().add(transferInfo.getAmount()));
                lock.notifyAll();
                return true;
            }
        }
        return false;
    }
}
