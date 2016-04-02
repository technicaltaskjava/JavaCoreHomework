package task1;

public class Assert {
    public static void assertEquals(Object expected, Object actual) {

        if (expected != null) {
            boolean equals = expected.equals(actual);
            if (!equals) {
                throw new AssertionException("Assertion fail");
            }
        } else {
            if (actual == null) {
                throw new AssertionException("Assertion fail");
            }
        }
    }

}
