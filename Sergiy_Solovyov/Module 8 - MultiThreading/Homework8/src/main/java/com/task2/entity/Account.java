package com.task2.entity;

import com.task2.exceptions.OverdraftException;

/**
 * @author Sergey Solovyov
 */
public class Account {

    private String accountNumber;
    private int balance;
    public static final String SEPARATOR = "--------------------------------------------------------";

    public Account(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount){
            balance = balance + amount;
    }

    public synchronized void withdraw(int amount) throws OverdraftException {
        int balAfter = balance - amount;
        if (balAfter < 0)
            throw new OverdraftException(String.format("Exception, balance after operation will be: %d, account: %s", balAfter, this.getAccountNumber()));
        balance = balAfter;
    }
    public synchronized void rollBack(int amount)  {
        balance = balance - amount;
        System.out.println("\n" + Account.SEPARATOR);
        System.out.println(String.format("Rollback operation: accountNumber - %s, balance after: %d ", accountNumber, balance));
        System.out.println(Account.SEPARATOR+"\n");
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}
