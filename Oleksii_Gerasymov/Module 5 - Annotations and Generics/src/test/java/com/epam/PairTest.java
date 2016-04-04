package com.epam;

import com.epam.t03.Pair;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PairTest {
    @Test
    public void unitTest() throws Exception {
        Pair myTuple = Pair.createPair("Test String", 12);
        assertEquals("java.lang.String", myTuple.getFirstType());
        assertEquals("java.lang.Integer", myTuple.getSecondType());
        assertEquals("Test String", myTuple.getFirstItem());
        assertEquals(12, myTuple.getSecondItem());
    }
}
