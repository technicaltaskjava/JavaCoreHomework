package com.kokhanyuk.thread.resource;

/**
 * MyAccount
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyAccount {
    private int balance;
    private int id;

    public MyAccount(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void add(int amount) {
        balance = balance + amount;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }

    public int getId() {
        return id;
    }
}
