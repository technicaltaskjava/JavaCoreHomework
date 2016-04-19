package com.kokhanyuk.thread.resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * MyAccountLock
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyAccountLock {
    private int balance;
    private int id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public MyAccountLock(int id, int balance) {
        this.balance = balance;
        this.id = id;
    }

    public int getBalance() {
        readLock.lock();
        try {
            return balance;
        } finally {
            readLock.unlock();
        }
    }

    public void add(int amount) {
        writeLock.lock();
        try {
            balance = balance + amount;
        } finally {
            writeLock.unlock();
        }
    }

    public void withdraw(int amount) {
        writeLock.lock();
        try {
            balance = balance - amount;
        } finally {
            writeLock.unlock();
        }
    }

    public int getId() {
        return id;
    }
}