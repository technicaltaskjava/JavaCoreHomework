package com.task1;

import com.task1.primefinders.RunnablePrimeFinder;
import com.task1.thread.PrimeFinder;
import org.junit.Test;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class RunnableFinderTest {

    @Test
    public void runTest() throws  InterruptedException {
        RunnablePrimeFinder runnableFinder = new RunnablePrimeFinder();
        runnableFinder.run(1 , 150, 3);
        Collections.sort(PrimeFinder.getPrimes(), new IntComparator());
        assertEquals(PrimeFinder.getPrimes().toString(), Constant.LIST);

    }
}
