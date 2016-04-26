package com.kokhanyuk.patterns.singleton;

/**
 * MyAccount
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyAccount {

    private int balance;
    private int id;
        MyLogger log = MyLogger.getLogger();

    public MyAccount(int id) {
        this.id = id;
        log.info("Account " + id + " is created");
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
        log.info("Account " + id + " balance is " + balance);
    }
}
