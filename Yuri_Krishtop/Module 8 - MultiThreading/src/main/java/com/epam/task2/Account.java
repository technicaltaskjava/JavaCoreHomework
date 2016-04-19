package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class Account {
    private int id;
    private double balance;

    public Account(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void changeBalance(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return "Account: "+id+" balance: "+ String.format("%.2f", balance);
    }
}
