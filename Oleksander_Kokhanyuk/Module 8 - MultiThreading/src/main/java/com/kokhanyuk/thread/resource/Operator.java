package com.kokhanyuk.thread.resource;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Operator
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Operator extends Thread {
    private List<String> transfer;
    private static Logger log = Logger.getLogger(ToRunBank.class);

    Operator(List<String> transfer) {
        this.transfer = transfer;
        start();
    }

    @Override
    public void run() {
        int from;
        int to;
        int sum;
        try {
            for (String st : transfer) {
                String[] trans = st.split("\\-\\-\\>");
                from = Integer.parseInt(trans[0].trim());
                to = Integer.parseInt(trans[1].trim());
                sum = Integer.parseInt(trans[2].trim());
                if (Bank.one.getId() == from && Bank.two.getId() == to) {
                    synchronized (Bank.one) {
                        Bank.one.withdraw(sum);
                    }
                    Bank.two.add(sum);
                }
                if (Bank.two.getId() == from && Bank.one.getId() == to) {
                    Bank.two.withdraw(sum);
                    synchronized (Bank.one) {
                        Bank.one.add(sum);
                    }
                }
            }
        } catch (NumberFormatException e) {
            log.warn(e.getMessage(), e);
        }
    }
}
