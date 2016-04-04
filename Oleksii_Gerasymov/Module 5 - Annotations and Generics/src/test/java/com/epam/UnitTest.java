package com.epam;

import com.epam.t03.Unit;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UnitTest {
   @Test
    public void unitTest() throws Exception {
       Unit myTuple = Unit.createUnit("Test String");
       assertEquals("java.lang.String", myTuple.getFirstType());
       assertEquals("Test String", myTuple.getFirstItem());
    }
}
