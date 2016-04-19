package com.task1;

import com.task1.primefinders.CallablePrimeFinder;
import org.junit.Test;
import java.util.Collections;
import static org.junit.Assert.assertEquals;


public class CallableFinderTest {

    @Test
    public void runTest() throws InterruptedException {
        CallablePrimeFinder callableFinder = new CallablePrimeFinder();
        callableFinder.run(1 , 150, 3);
        Collections.sort(callableFinder.getPrimes(), new IntComparator());
        assertEquals(callableFinder.getPrimes().toString(), Constant.LIST);

    }
}
