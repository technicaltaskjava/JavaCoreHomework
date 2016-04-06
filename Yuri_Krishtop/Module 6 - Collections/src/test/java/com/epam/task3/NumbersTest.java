package com.epam.task3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yuriy Krishtop on 03.04.2016.
 */
public class NumbersTest {

    @Test
    public void numbersTest() {
        Numbers num = new Numbers();
        num.addNum(3);
        num.addNum(6);
        num.addNum(12.8);
        num.addNum(-12.8);
        num.addNum(5.9);
        num.addNum(4.98);
        assertEquals(3, num.getNumbers().get(0));
        num.delNum(3);
        assertEquals(6, num.getNumbers().get(0));
        assertEquals(4.98, num.findNearest(5));
    }
}
