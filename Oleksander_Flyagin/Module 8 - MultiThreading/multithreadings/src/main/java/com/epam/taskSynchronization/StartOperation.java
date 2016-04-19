package com.epam.taskSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StartOperation {
    public void trancsaction(Account account1, Account account2, int deposit) {
        System.out.println(account1);
        System.out.println(account2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Trancaction(account1, account2, deposit));
        executor.execute(new CheckBalance(account1, deposit));
        executor.shutdown();

    }

}



