package com.epam.task2;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oleg on 20.04.2016.
 */
public class Run {

    private static final String FILE_NAME = "1.txt";

    private Run() {
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Account account1 = new Account(300);
        Account account2 = new Account(300);
        System.out.println("Initial values of accounts:");
        showAccounts(account1, account2);

        FileProcess fileProcessSynchronized1 = new FileProcess(account1, account2, FILE_NAME, 1, 2, false);
        FileProcess fileProcessSynchronized2 = new FileProcess(account1, account2, FILE_NAME, 3, 4, false);

        runThreads(new Thread(fileProcessSynchronized1), new Thread(fileProcessSynchronized2));
        System.out.println("Values of account after synchronized processing with values from file:");
        showAccounts(account1, account2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        FileProcess fileProcessConcurrent1 = new FileProcess(account1, account2, FILE_NAME, 1, 2, true);
        FileProcess fileProcessConcurrent2 = new FileProcess(account1, account2, FILE_NAME, 3, 4, true);
        FileProcess fileProcessConcurrent3 = new FileProcess(account1, account2, FILE_NAME, 5, 5, true);
        executorService.execute(fileProcessConcurrent1);
        executorService.execute(fileProcessConcurrent2);
        executorService.execute(fileProcessConcurrent3);
        executorService.shutdownNow();
        TimeUnit.MILLISECONDS.sleep(10);

        System.out.println("Values of account after concurrent processing with values from file:");
        showAccounts(account1, account2);
    }

    public static void runThreads(Thread t1, Thread t2) throws InterruptedException {
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void showAccounts(Account account1, Account account2) {
        account1.showAll();
        account2.showAll();
    }
}
