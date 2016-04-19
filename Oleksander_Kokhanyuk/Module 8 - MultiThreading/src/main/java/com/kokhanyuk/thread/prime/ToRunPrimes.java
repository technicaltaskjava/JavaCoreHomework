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
        prime.calculate();
    }
}
