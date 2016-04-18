package com.epam;

import com.epam.first.SearchingThread;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

public class NumberFinderTest {
    private static Logger log = Logger.getLogger(NumberFinderTest.class.getName());

    @Test
    public void numbersTest() {
        SortedSet<Integer> simpleNumbersSet = new TreeSet<>();
        SearchingThread thread1 = new SearchingThread(2, 12, simpleNumbersSet);
        SearchingThread thread2 = new SearchingThread(13, 24, simpleNumbersSet);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException userException) {
            log.info(String.valueOf(userException));
        }
        assertEquals(9, simpleNumbersSet.size());
        assertEquals(2, simpleNumbersSet.first().intValue());
        assertEquals(23, simpleNumbersSet.last().intValue());
    }
}
