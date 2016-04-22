package com.epam.taskSynchronization;


public class Trancaction implements Runnable {
    Account account1;
    Account account2;
    private int sum;


    public Trancaction(Account account1, Account account2, int sum) {
        this.account1 = account1;
        this.account2 = account2;
        this.sum = sum;
    }

    @Override
    public void run() {
        account1.withdraw(sum);
        account2.deposit(sum);
        System.out.println(account1);
        System.out.println(account2);

    }
}


