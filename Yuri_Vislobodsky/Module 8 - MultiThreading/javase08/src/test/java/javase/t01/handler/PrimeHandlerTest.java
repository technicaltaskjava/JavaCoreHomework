package javase.t01.handler;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for PrimeHandler class
 * Checks count of prime numbers, which were found by two methods
 * Created by Yury Vislobodsky on 18.04.2016.
 */
public class PrimeHandlerTest {
    PrimeHandler primeHandler;

    @Before
    public void setUp() {
        primeHandler = new PrimeHandler(1, 1000, 4);
    }

    @Test
    public void testPrimeHandlerExecute() throws InterruptedException {
        primeHandler.execute(PrimeHandlerMethod.METHOD1);
        assertEquals("error in Method1", 169, primeHandler.getSize());

        primeHandler.execute(PrimeHandlerMethod.METHOD2);
        assertEquals("error in Method2", 169, primeHandler.getSize());
    }
}