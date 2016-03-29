package com.epam;

import com.epam.t02.CompareUtils;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SortingTest {
    @Test
    public void unitTest() throws Exception {
        Integer[] testIntArray = {23, 11, 55, 9, 32};
        String[] testStringArray = {"First","Second","Third","Fourth","Fifth"};

        assertEquals (9, (int) CompareUtils.minElement(testIntArray));
        assertEquals (55, (int) CompareUtils.maxElement(testIntArray));
        assertEquals (23, (int) CompareUtils.medianElement(testIntArray));
        assertEquals (9, (int) CompareUtils.minElementByComparator(testIntArray));
        assertEquals (55, (int) CompareUtils.maxElementByComparator(testIntArray));
        assertEquals (23, (int) CompareUtils.medianElementByComparator(testIntArray));
        assertEquals ("Fifth", CompareUtils.minElement(testStringArray));
        assertEquals ("Third", CompareUtils.maxElement(testStringArray));
        assertEquals ("Fourth", CompareUtils.medianElement(testStringArray));
        assertEquals ("Fifth", CompareUtils.minElementByComparator(testStringArray));
        assertEquals ("Third", CompareUtils.maxElementByComparator(testStringArray));
        assertEquals ("Fourth", CompareUtils.medianElementByComparator(testStringArray));
    }
}
