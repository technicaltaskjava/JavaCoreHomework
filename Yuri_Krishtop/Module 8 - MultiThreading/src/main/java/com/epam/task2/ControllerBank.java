package com.epam.task2;

import com.epam.Main;
import org.apache.log4j.Logger;


/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class ControllerBank {

    private static final Logger log = Logger.getLogger(ControllerBank.class);

    private ControllerBank() {}

    public static void main(boolean flag) {
        System.out.println(Main.LINE);
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Creating Bank with 100 accounts and set up initial balance 5000" +
                " for each of ones", "|");
        Bank bank;
        if(flag) {
            bank = new Bank(100, 5000);
        } else {
            bank = new BankConcurrent(100, 5000);
        }
        bank.setTransactionsFileName("src/main/resources/transactions.txt");
        bank.readTextFromFile();
        bank.setTransactions(bank.createTransaction(bank.readTextFromFile()));
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Count of transactions: "+ bank.getTransactions().size(), "|");
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Initial total balance: " + bank.getTotalBalance(), "|");
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Transactions are running... ", "|");
        System.out.println(Main.LINE);
        TransferRunnable[] transfersRunnable = new TransferRunnable[cores];
        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            transfersRunnable[i] = new TransferRunnable(bank, i);
            threads[i] = new Thread(transfersRunnable[i]);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error(e);
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Main.LINE);
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Count of unused transactions: " + bank.getTransactions().size(), "|");
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Finale total balance: " + String.format("%.2f", bank.getTotalBalance()), "|");
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Accounts after transactions: ", "|");
        System.out.println(Main.LINE);
        for(Account a : bank.getAccounts()) {
            System.out.printf(Main.FORMAT_STRING_PIPE, "| " + a.toString(), "|");
        }
        System.out.println(Main.LINE);
    }
}
