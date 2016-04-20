package com.kokhanyuk.thread.prime;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Primes
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Primes {

    private static Logger log = Logger.getLogger(Primes.class);
    static final Set<Integer> resultOne = new TreeSet();
    static final Set<Integer> resultTwo = new TreeSet();

    public void calculate() {

        int start = 0;
        int end = 0;
        int threads = 0;
        Scanner in = new Scanner(System.in);
        try {
            log.info("Enter start number:");
            start = in.nextInt();
            log.info("Enter end number:");
            end = in.nextInt();
            log.info("Enter the numbers of threads:");
            threads = in.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Error input data: ", e);
        }

        if (checkData(start, end, threads)) {
            PrimesManager one = new PrimesManager(start, end, threads, true);
            PrimesManager two = new PrimesManager(start, end, threads, false);
            try {
                one.join();
            } catch (InterruptedException e) {
                log.warn(e.getMessage(),e);
                one.interrupt();
                Thread.currentThread().interrupt();
            }
            try {
                two.join();
            } catch (InterruptedException e) {
                log.warn(e.getMessage(),e);
                two.interrupt();
                Thread.currentThread().interrupt();
            }
        }
        in.close();
        log.info("Entry end is true  :" + Primes.resultOne);
        log.info("Entry end is false :" + Primes.resultTwo);
    }

    static boolean checkData(int start, int end, int threads) {
        if (start > 1 && start < end && (end - start) > threads && end < 100000) {
            return true;
        }
        return false;
    }

}
