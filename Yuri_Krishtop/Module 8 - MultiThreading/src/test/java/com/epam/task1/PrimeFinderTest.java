package com.epam.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuriy Krishtop on 15.04.2016.
 */
public class PrimeFinderTest {

    @Test
    public void isPrime() {
        assertTrue(FindPrimeThread.isPrime(13));
        assertFalse(FindPrimeThread.isPrime(4));
    }

    @Test
    public void countPrime() {
        assertEquals(168, FindPrimeThread.countPrimeNumber(1, 1000));
    }

    @Test
    public void calcLimitForThreads() {
        assertEquals(500, FindPrimeThread.calcLimitForThreads(1, 1000, 2)[1]);
    }

}