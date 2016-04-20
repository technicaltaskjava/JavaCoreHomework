package com.kokhanyuk.thread.prime;


/**
 * ToRunPrimes
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunPrimes {
    private ToRunPrimes() {
    }

    public static void main(String[] args) {
        Primes prime = new Primes();
         try {
            prime.calculate();
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
