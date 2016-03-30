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
    public static final String FAIL = "Object do not equals";
    private Apple apple1 = new Apple(3);
    private Apple apple2 = new Apple(9);
    private Apple apple3 = new Apple(-9);
    private Apple[] apples = new Apple[]{apple1, apple2, apple3};

    @Test
    public void testMin() throws WrongArgumentException {

        Apple apple = CompareUtils.min(apples);
        Assert.assertEquals(FAIL, apple.equals(apple3), true);
    }
    @Test
    public void testMinFail() throws WrongArgumentException {

      Apple apple = CompareUtils.min(apples);
        Assert.assertNotEquals(FAIL, apple.equals(apple3), false);
    }

    @Test
    public void testMinComparator() throws WrongArgumentException {
        Apple apple = CompareUtils.min(apples, new Apple());
        Assert.assertEquals(FAIL, apple.equals(apple3), true);
    }

    @Test
    public void testMinComparatorFail() throws WrongArgumentException {
        Apple apple = CompareUtils.min(apples, new Apple());
        Assert.assertNotEquals(FAIL, apple.equals(apple3), false);
    }

    @Test
    public void testMax() throws WrongArgumentException {
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertEquals(FAIL, apple.equals(apple2), true);
    }
    @Test
    public void testMaxFail() throws WrongArgumentException {
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertNotEquals(FAIL, apple.equals(apple2), false);
    }

    @Test
    public void testMaxComparator() throws WrongArgumentException {
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertEquals(FAIL, apple.equals(apple2), true);
    }
    @Test
    public void testMaxComparatorFail() throws WrongArgumentException {
        Apple apple = CompareUtils.max(apples, new Apple());
        Assert.assertNotEquals(FAIL, apple.equals(apple2), false);
    }

    @Test
    public void testMedian() throws WrongArgumentException {
        Apple apple = CompareUtils.median(apples);
        Assert.assertEquals(FAIL, apple.equals(apple), true);
    }
    @Test
    public void testMedianFail() throws WrongArgumentException {
        Apple apple = CompareUtils.median(apples);
        Assert.assertNotEquals(FAIL, apple.equals(apple), false);
    }

    @Test
    public void testMedianComparator() throws WrongArgumentException {
        Apple apple = CompareUtils.median(apples, new Apple());
        Assert.assertEquals(FAIL, apple.equals(apple), true);
    }

    @Test
    public void testMedianComparatorFail() throws WrongArgumentException {
        Apple apple = CompareUtils.median(apples, new Apple());
        Assert.assertNotEquals(FAIL, apple.equals(apple), false);

    }


}
