package tester.assertion;

/**
 * @author Alexey Ushakov
 */
public class Assert {
    public static void assertTrue(boolean assertion) {
        System.out.println("Parameter of method assertTrue is " + assertion);
        if (!assertion) {
            throw new AssertException("Parameter not true");
        }
    }

    public static void assertFalse(boolean assertion) {
        System.out.println("Parameter of method assertFalse is " + assertion);
        if (assertion) {
            throw new AssertException("Parameter not false");
        }
    }

    public static void assertEquals(int first, int second) {
        System.out.println("Parameter of method assertEquals is " + first + " and " + second);
        if (first != second) {
            throw new AssertException("Parameter not equals");
        }
    }
}
