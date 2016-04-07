package com.task3;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberHolderTest {

    private NumbersHolder<Integer> myStructure = new NumbersHolder<>();

    @Before
    public void testAdd(){
        myStructure.addNumber(1);
        myStructure.addNumber(9);
        myStructure.addNumber(13);
        myStructure.addNumber(2);
        myStructure.addNumber(5);
        myStructure.addNumber(7);
        myStructure.addNumber(111);
    }
    @Test
    public void testToString(){
        assertEquals(myStructure.toString(), "[1, 9, 13, 2, 5, 7, 111]");
    }
    @Test
    public void testFindClosestNumber()  {
        assertEquals(myStructure.findClosestNumber(Integer.MIN_VALUE), new Integer(1));
        assertEquals(myStructure.findClosestNumber(5), new Integer(5));
        assertEquals(myStructure.findClosestNumber(14), new Integer(13));
        assertEquals(myStructure.findClosestNumber(100), new Integer(111));
        assertEquals(myStructure.findClosestNumber(Integer.MAX_VALUE), new Integer(111));

    }
}
