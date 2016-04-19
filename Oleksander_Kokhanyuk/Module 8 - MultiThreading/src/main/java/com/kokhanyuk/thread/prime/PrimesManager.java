package com.kokhanyuk.thread.prime;

import org.apache.log4j.Logger;

/**
 * PrimesManager
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class PrimesManager extends Thread {

    private long startTime;
    private long endTime;
    private int start;
    private int end;
    private int threads;
    private boolean entryEnd;

    static Logger log = Logger.getLogger(Primes.class);

    PrimesManager(int start, int end, int threads, boolean entryEnd) {
        this.start = start;
        this.end = end;
        this.threads = threads;
        this.entryEnd = entryEnd;
        startTime = System.currentTimeMillis();
        start();
    }

    @Override
    public void run() {
        int step = (end - start) / threads;
        int first = start;
        int last = step;
        CalculatePrimes[] calcs = new CalculatePrimes[threads];
        startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            calcs[i] = new CalculatePrimes(first, last, entryEnd);
            first = last + 1;
            last += step;
            if (last > end) {
                last = end;
            }
        }
        for (Thread t : calcs) {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.warn(e.getMessage(), e);
                t.interrupt();
            }
        }
        endTime = System.currentTimeMillis();
        log.info(endTime - startTime + "-ms total time if Entry end is " + entryEnd);
    }
}
