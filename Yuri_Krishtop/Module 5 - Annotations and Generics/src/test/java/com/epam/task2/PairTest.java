package com.epam.task2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 27.03.2016.
 */
public class PairTest {

    @Test
    public void pairTestInt() {
        Pair test = Pair.create(1, "test");
        Assert.assertEquals(1, test.getFirst());
    }

    @Test
    public void pairTestString() {
        Pair test = Pair.create(1, "test");
        Assert.assertEquals("test", test.getSecond());
    }
}
