package com.epam.task3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Olga Kramska on 03-Apr-16.
 */
public class NumbersStructureTest {
    private NumbersStructure<Integer> numbersStructure;

    @Before
    public void init() {
        final Integer[] data = {1, 3, 2, 6, 4, 7, 8, 11, 12};
        numbersStructure = new NumbersStructure<>(Arrays.asList(data));
    }

    @Test
    public void testAddNumber() {
        assertTrue(numbersStructure.addNumber(10));
    }

    @Test
    public void testRemoveNumber() {
        assertFalse(numbersStructure.removeNumber(10));
        assertTrue(numbersStructure.removeNumber(8));
    }

    @Test
    public void testFindClosestNumber() {
        assertEquals(new Integer(11), numbersStructure.findClosestNumber(10));
    }
}
