package com.kokhanyuk.thread.prime;

import java.util.Set;
import java.util.TreeSet;

/**
 * CalculatePrimes
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CalculatePrimes extends Thread {

    private Set<Integer> primes = new TreeSet();
    private int first;
    private int end;
    private boolean entryEnd;

    CalculatePrimes(int first, int end, boolean entryEnd) {
        this.first = first;
        this.end = end;
        this.entryEnd = entryEnd;
        start();
    }

    @Override
    public void run() {
        boolean prime;
        int number;
        for (number = first; number <= end; number++) {
            prime = true;
            for (int j = 2; j < number / 2; j++) {
                if (number % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                if (entryEnd) {
                    primes.add(number);
                } else {
                    synchronized (Primes.resultOne) {
                        Primes.resultTwo.add(number);
                    }
                }

            }
        }
        if (entryEnd) {
            synchronized (Primes.resultOne) {
                Primes.resultOne.addAll(primes);
            }
        }
    }
}

