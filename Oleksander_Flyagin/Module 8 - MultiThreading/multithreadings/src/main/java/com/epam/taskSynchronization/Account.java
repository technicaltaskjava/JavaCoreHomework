package com.epam.taskSynchronization;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Account {
    private Logger myLog = Logger.getLogger("Loger");


    public Account(String name) {
        this.name = name;
    }

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }


    private String name;
    private static Lock lock = new ReentrantLock();
    private static Condition newDeposit = lock.newCondition();
    private int balance;
    public volatile  boolean balanceStatus = true;


    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance < amount) {
                System.out.println("не хватает суммы в " + amount + ", нет возможности провести операцию ");
                balanceStatus = false;
                newDeposit.await();
            } else {
                balance -= amount;
                System.out.println(name + " передал  сумму " + amount + "  остаток " + getBalance());
            }
        } catch (InterruptedException e) {
            myLog.warning("WORNING" + e);
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(name + " получил перевод на сумму  " + amount + " баксов");
            newDeposit.signalAll();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public String toString() {
        return "Account name " + getName() + " Остаток " + getBalance();
    }
}

