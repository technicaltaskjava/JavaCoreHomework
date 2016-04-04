package tester;

import tester.assertion.Assert;

/**
 * @author Alexey Ushakov
 */

@SuppressWarnings("unused")
public class TestedClass {

    @Test
    public static void testAssertTrue() {
        Assert.assertTrue(true);
    }

    @Test
    public static void testAssertTrueFail() {
        Assert.assertTrue(false);
    }

    @Test
    public static void testAssertEquals() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public static void testAssertEqualsFail() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public static void testAssertFalse() {
        Assert.assertFalse(false);
    }

    @Test
    public static void testThrowCustomException() {
        throw new IndexOutOfBoundsException();
    }

    @Test(expected = IllegalArgumentException.class)
    public static void testThrowCorrectException() {
        throw new IllegalArgumentException();
    }

    @Test(expected = IllegalArgumentException.class)
    public static void testThrowInCorrectException() {
        throw new ArrayIndexOutOfBoundsException();
    }

    @Test(ignore = true)
    public void ignoredTest() {
        //NOP
    }

    public void notTest() {
        //NOP
    }
}
