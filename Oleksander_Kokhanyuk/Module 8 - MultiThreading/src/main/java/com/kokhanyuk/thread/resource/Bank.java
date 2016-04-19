package com.kokhanyuk.thread.resource;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Bank
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Bank {
    private static Logger log = Logger.getLogger(ToRunBank.class);
    static final MyAccount one = new MyAccount(10000001, 1000);
    static final MyAccountLock two = new MyAccountLock(20000002, 2000);

    public void operations() {
        log.info("Account one " + Bank.one.getId() + " :" + Bank.one.getBalance() + " $");
        log.info("Account two " + Bank.two.getId() + " :" + Bank.two.getBalance() + " $");
        FileIO file = new FileIO();
        String transfers = file.readFile("mybank.txt");
        String[] transfer = transfers.split("\\$");
        List<String> transferList = Arrays.asList(transfer);
        int threads = 1 + Runtime.getRuntime().availableProcessors();
        int step = transferList.size() / threads;
        int start = 0;
        int end = step;
        Thread[] operator = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            if (i == threads - 1) {
                end = transferList.size();
            }
            operator[i] = new Operator(transferList.subList(start, end));
            start = end + 1;
            end += step;
        }
        for (Thread t : operator) {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.warn(e.getMessage(), e);
                t.interrupt();
            }
        }
        log.info("Account one " + Bank.one.getId() + " :" + Bank.one.getBalance() + " $");
        log.info("Account two " + Bank.two.getId() + " :" + Bank.two.getBalance() + " $");
    }

}
