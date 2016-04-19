package com.epam.task2.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public abstract class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void addAccount(int accountNumber) {
        addAccount(accountNumber, BigDecimal.ZERO);
    }

    public void addAccount(int accountNumber, BigDecimal valueOfAccount) {
        accounts.put(accountNumber, new Account(accountNumber, valueOfAccount));
    }

    public abstract boolean transfer(TransferInfo transferInfo) throws InterruptedException;

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
