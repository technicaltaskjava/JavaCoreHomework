package com.task2.application;

import com.task2.entity.Account;
import com.task2.parser.TextParser;
import com.task2.thread.MyThread;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Solovyov
 */
public class Application {

    private TextParser parser;

    public Application(TextParser parser) {
        this.parser = parser;
    }

    public void run() throws InterruptedException {
        parser.loadAccounts("src/main/resources/accountInit.txt");
        System.out.println("Init parameters: ");

        printAccounts();
        Thread.sleep(1500);
        System.out.println("\nWARNING! There will be OverdraftException\n");
        Thread.sleep(1500);
        int cores = Runtime.getRuntime().availableProcessors();
        parser.loadTransactions("src/main/resources/TransactionList.txt");
        System.out.printf("%d cores detected, %d thread will be launched", cores, cores);
        System.out.println("\n");
        Thread.sleep(1500);

        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < cores; i++){
            executorService.execute(new MyThread(parser.getTransactionList()));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("After " + MyThread.COUNTER +" transactions:\n");
        printAccounts();
    }

    private void printAccounts(){
        for (Map.Entry<String, Account>entry: TextParser.accounts.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
