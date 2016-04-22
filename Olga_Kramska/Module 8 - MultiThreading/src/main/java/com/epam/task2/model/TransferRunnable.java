package com.epam.task2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class TransferRunnable implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(TransferRunnable.class);

   private TransferInfo transferInfo;
    private Bank bank;

    public TransferRunnable(Bank bank, TransferInfo transferInfo) {
        this.bank = bank;
        this.transferInfo = transferInfo;
    }

    @Override
    public void run() {
        try {
            bank.transfer(transferInfo);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
