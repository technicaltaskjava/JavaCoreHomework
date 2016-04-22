package com.epam.second;

public class Account {
    private String numberOfAccount;
    private int balance;

    public Account(String numberOfAccount, int balance) {
        this.balance = balance;
        this.numberOfAccount = numberOfAccount;
    }

    public int getBalance() {
        return balance;
    }

    public String getNumberOfAccount() {
        return numberOfAccount;
    }

    public synchronized void synchronizedDeposit(int balance) {
        this.balance = this.balance + balance;
    }

    public synchronized void synchronizedWithdraw(int balance) {
        this.balance = this.balance - balance;
    }

    public void concurrentDeposit(int balance) {
        this.balance = this.balance + balance;
    }

    public void concurrentWithdraw(int balance) {
        this.balance = this.balance - balance;
    }


    public String outAccountData() {
        return String.format("%-20s%-8d", this.getNumberOfAccount(),this.getBalance());
    }
}
