package tester.assertion;

/**
 * @author Alexey Ushakov
 */
public class Assert {
    private Assert() {
    }

    public static void assertTrue(boolean assertion) {
        System.out.println("\nParameter of method assertTrue is " + assertion);
        if (!assertion) {
            throw new AssertException("Parameter not true");
        }
    }

    public static void assertFalse(boolean assertion) {
        System.out.println("\nParameter of method assertFalse is " + assertion);
        if (assertion) {
            throw new AssertException("Parameter not false");
        }
    }

    public static void assertEquals(int first, int second) {
        System.out.println("\nParameter of method assertEquals is " + first + " and " + second);
        if (first != second) {
            throw new AssertException("Parameter not equals");
        }
    }
}
