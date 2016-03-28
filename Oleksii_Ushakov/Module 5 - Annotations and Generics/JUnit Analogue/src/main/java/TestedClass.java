import tester.Test;
import tester.assertion.Assert;

/**
 * @author Alexey Ushakov
 */
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
        throw new RuntimeException();
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
    public static void ignoredTest() {
        System.out.println("If you see this message it means there was some error somewhere");
    }

    public static void notTest() {
        System.out.println("If you see this message it means there was some error somewhere");
    }
}
