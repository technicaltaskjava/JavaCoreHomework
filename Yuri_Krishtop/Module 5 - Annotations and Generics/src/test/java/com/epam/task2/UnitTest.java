package com.epam.task2;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Yuriy Krishtop on 27.03.2016.
 */
public class UnitTest {

    @Test
    public void unitTestInt() {
        Unit test = Unit.create(1);
        Assert.assertEquals(1, test.getFirst());
    }

    @Test
    public void unitTestString() {
        Unit test = Unit.create("test");
        Assert.assertEquals("test", test.getFirst());
    }

}
