package tests.task2;

import task1.Assert;
import task1.Test;
import task2.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class CompareUtilsTest {
    private Apple apple1 = new Apple(3);
    private Apple apple2 = new Apple(9);
    private Apple apple3 = new Apple(-9);
    private Apple[] apples = new Apple[]{apple1, apple2, apple3};

    @Test
    public void testMin(){

        Apple apple = CompareUtils.min(apples);
        Assert.assertEquals("Object do not equals", apple.equals(apple3), true);
    }
    @Test
    public void testMinFail(){

      Apple apple = CompareUtils.min(apples);
        Assert.assertNotEquals("Object do not equals", apple.equals(apple3), false);
    }

    @Test
    public void testMinComparator(){
        Apple apple = CompareUtils.min(apples, new Apple());
        Assert.assertEquals("Object do not equals",apple.equals(apple3), true);
    }

    @Test
    public void testMinComparatorFail(){
        Apple apple = CompareUtils.min(apples, new Apple());
        Assert.assertNotEquals("Object do not equals",apple.equals(apple3), false);
    }

    @Test
    public void testMax(){
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertEquals("Object do not equals", apple.equals(apple2), true);
    }
    @Test
    public void testMaxFail(){
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertNotEquals("Object do not equals", apple.equals(apple2), false);
    }

    @Test
    public void testMaxComparator(){
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertEquals("Object do not equals", apple.equals(apple2), true);
    }
    @Test
    public void testMaxComparatorFail(){
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertNotEquals("Object do not equals", apple.equals(apple2), false);
    }

    @Test
    public void testMedian(){
        Apple apple = CompareUtils.median(apples);
        Assert.assertEquals("Object do not equals", apple.equals(apple), true);
    }
    @Test
    public void testMedianFail(){
        Apple apple = CompareUtils.median(apples);
        Assert.assertNotEquals("Object do not equals", apple.equals(apple), false);
    }

    @Test
    public void testMedianComparator(){
        Apple apple = CompareUtils.median(apples, new Apple());
        Assert.assertEquals("Object do not equals", apple.equals(apple), true);
    }

    @Test
    public void testMedianComparatorFail(){
        Apple apple = CompareUtils.median(apples, new Apple());
        Assert.assertNotEquals("Object do not equals", apple.equals(apple), false);

    }


}
