package com.epam.task2;

import com.epam.task2.model.TransferRunnable;
import com.epam.task2.model.concurrent.BankWithLock;
import com.epam.task2.model.Bank;
import com.epam.task2.model.TransferInfo;
import com.epam.task2.model.sync.BankSync;
import com.epam.task2.util.BankUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class Main {
    private Main() {
    }

    /**
     * For running the app you have to specify type of Bank synchronization using command line args.
     * To use synchronized version of Bank you should set `sync` value of args
     * or `lock` to use Bank with java.util.concurrent framework
     *
     * @param args type synchronized version of Bank.
     * @throws IllegalArgumentException If the argument of command line is empty or incorrect value
     * @throws InterruptedException     if any thread has interrupted the current thread
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args are not set");
        }

        String type = args[0];
        Bank bank = createBank(type);
        initializeBank(bank);
        System.out.println(bank);

        List<TransferInfo> transferInfoList = BankUtil.readTransferInfoFromFile("transfers.txt");
        for (TransferInfo transferInfo : transferInfoList) {
            TransferRunnable transferRunnable = new TransferRunnable(bank, transferInfo);
            Thread thread = new Thread(transferRunnable);
            thread.start();
            thread.join();
        }
        System.out.println(bank);
    }

    private static Bank createBank(String type) {
        if ("sync".equals(type)) {
            return new BankSync();
        } else if ("lock".equals(type)) {
            return new BankWithLock();
        }
        throw new IllegalArgumentException("Please set command line args to `sync` or `lock` value");
    }

    private static void initializeBank(Bank bank) {
        bank.addAccount(10001, new BigDecimal(10000));
        bank.addAccount(10002, new BigDecimal(1000));
        bank.addAccount(10003, new BigDecimal(1000));
        bank.addAccount(10004, new BigDecimal(100));
        bank.addAccount(10005, new BigDecimal(2500));
        bank.addAccount(10006, new BigDecimal(1000));
        bank.addAccount(10007, new BigDecimal(1000));
        bank.addAccount(10008, new BigDecimal(1000));
        bank.addAccount(10009, new BigDecimal(1000));
        bank.addAccount(10010, new BigDecimal(1000));
    }
}
