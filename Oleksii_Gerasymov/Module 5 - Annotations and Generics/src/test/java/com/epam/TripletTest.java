package com.epam;

import com.epam.t03.Triplet;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TripletTest {
    @Test
    public void unitTest() throws Exception {
        Triplet myTuple = Triplet.createTriplet("Test String", 12, 'c');
        assertEquals("java.lang.String", myTuple.getFirstType());
        assertEquals("java.lang.Integer", myTuple.getSecondType());
        assertEquals("java.lang.Character", myTuple.getThirdType());
        assertEquals("Test String", myTuple.getFirstItem());
        assertEquals(12, myTuple.getSecondItem());
        assertEquals('c', myTuple.getThirdItem());
    }
}
