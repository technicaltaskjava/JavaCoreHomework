package com.epam;

import com.epam.task.three.NumberSet;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SetTest {
    @Test
    public void setTest() {
        NumberSet testSet = new NumberSet();
        testSet.addElement(24.1);
        testSet.addElement(-14.2);
        testSet.addElement(12.6);
        testSet.addElement(-8.3);
        testSet.removeElement(24.1);
        assertEquals("-14.2, 12.6, -8.3, ", testSet.getSet());
        assertEquals(3, testSet.getLength());
        assertEquals(-14.2, testSet.seekNear(-11.5));
    }
}
