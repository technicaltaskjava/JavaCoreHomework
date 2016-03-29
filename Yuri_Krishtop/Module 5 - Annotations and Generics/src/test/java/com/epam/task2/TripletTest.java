package com.epam.task2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 27.03.2016.
 */
public class TripletTest {
    @Test
    public void tripletTestFirst() {
        Triplet test = Triplet.create(1, "test", 4.5);
        Assert.assertEquals(1, test.getFirst());
    }

    @Test
    public void pairTestSecond() {
        Triplet test = Triplet.create(1, "test", 4.5);
        Assert.assertEquals("test", test.getSecond());
    }

    public void tripletTestThird() {
        Triplet test = Triplet.create(1, "test", 4.5);
        Assert.assertEquals(4.5, test.getThird());
    }
}
