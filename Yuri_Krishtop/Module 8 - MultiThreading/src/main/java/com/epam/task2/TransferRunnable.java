package com.epam.task2;

import com.epam.Main;

/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class TransferRunnable implements Runnable {

    private Bank bank;
    private int threadNum;

    public TransferRunnable(Bank bank, int threadNum) {
        this.bank = bank;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        while (bank.removeTransaction() != null) {
            Transaction transaction = bank.removeTransaction();
            System.out.printf(Main.FORMAT_STRING_PIPE, "| Thread: " + threadNum + "  " +
                    "sender:  " + transaction.getSender() +
                    " recipient: " + transaction.getRecipient() +
                    " amount: " + transaction.getAmount(), "|");
            bank.transfer(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
        }
    }
}
