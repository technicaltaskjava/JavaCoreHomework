package com.epam.task3;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 28.03.2016.
 */
public class CompareUtilsTest {

    @Test
    public void testMin() {
        Integer[] ar = {23, 78, 2, 45, 4};
        Integer min = 2;
        Assert.assertEquals(min, CompareUtils.min(ar));
    }

    @Test
    public void testMax() {
        Integer[] ar = {23, 78, 2, 45, 4};
        Integer max = 78;
        Assert.assertEquals(max, CompareUtils.max(ar));
    }

    @Test
    public void testMedian() {
        Integer[] ar = {23, 78, 2, 45, 4};
        Integer median = 23;
        Assert.assertEquals(median, CompareUtils.median(ar));
    }

}
