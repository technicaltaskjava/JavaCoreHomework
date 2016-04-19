package number;

import interval.Interval;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class PrimeNumberTest {

    @Test
    public void testIsPrimeNumberTrue() {
        assertTrue(PrimeNumber.isPrimeNumber(2));
    }

    @Test
    public void testIsPrimeNumberFalse()  {
        assertFalse(PrimeNumber.isPrimeNumber(4));
    }

    @Test
    @Ignore("Intel® Core™ i5-3230M CPU @ 2.60GHz × 4   => 5h 26m 35s 325ms => 1 Thread")
    public void testIsPrimeNumbers()  {
        Interval interval = new Interval(2, Integer.MAX_VALUE);
        int count = 0;

        for (int i = interval.getLeftBorder(); i > 0 && i <= interval.getRightBorder(); i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                count++;
            }
        }

        assertEquals(count, 105075114);
    }

    @Test
    public void testIsPrimeNumbersOnTenThousand()  {
        Interval interval = new Interval(2, 10000);
        int count = 0;

        for (int i = interval.getLeftBorder(); i > 0 && i <= interval.getRightBorder(); i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                count++;
            }
        }

        assertEquals(count, 1229);
    }

    @Test
    @Ignore
    public void testIsPrimeNumbersOnOneHundredThousand()  {
        Interval interval = new Interval(2, 100000000);
        int count = 0;

        for (int i = interval.getLeftBorder(); i > 0 && i <= interval.getRightBorder(); i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                count++;
            }
        }

        assertEquals(count, 5761455);
    }
}