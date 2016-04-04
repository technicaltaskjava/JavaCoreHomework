package com.epam.task2;

import org.junit.Test;

import java.util.Comparator;

import static com.epam.task2.CompareUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 28-Mar-16.
 */
public class CompareUtilsTest {

    private final Integer[] data = {1, 3, 2, 6, 4, 7, 8};

    @Test
    public void testMax() {
        assertEquals(max(data), new Integer(8));
        assertEquals(max(data, new SortInt()), new Integer(8));
    }

    @Test
    public void testMin() {
        assertEquals(min(data), new Integer(1));
        assertEquals(min(data, new SortInt()), new Integer(1));
    }

    @Test
    public void testMedian() {
        assertEquals(median(data), new Integer(4));
        assertEquals(median(data, new SortInt()), new Integer(4));
    }

    private class SortInt implements Comparator<Integer> {
        @Override
        public int compare(Integer d1, Integer d2) {
            return d1.compareTo(d2);
        }
    }
}
