package t01.model.assertion;

import t01.exception.AssertionFail;

public class Assert {
	private Assert() {
	}

	public static void assertEquals(final Object expected, final Object actual) {

		if (expected != null) {
			boolean equals = expected.equals(actual);
			if (!equals) {
				throw new AssertionFail(getMessageResult(expected, actual));
			}
		} else {
			if (actual == null) {
				throw new AssertionFail(getMessageResult(expected, actual));
			}
		}
	}

	public static void assertTrue(final boolean condition) {
		if (!condition) {
			throw new AssertionFail(getMessageResult(true, condition));
		}
	}

	public static void assertFalse(final boolean condition) {
		if (condition) {
			throw new AssertionFail(getMessageResult(false, condition));
		}
	}

	public static void assertNull(final Object object) {
		if (object != null) {
			throw new AssertionFail(getMessageResult(null, object));
		}
	}

	public static void assertNotNull(final Object object) {
		if (object == null) {
			throw new AssertionFail(getMessageResult(object, null));
		}
	}

	public static void fail() {
		throw new AssertionFail("Invoke fail method");
	}

	private static String getMessageResult(final Object expected, final Object actual) {
		String expectedMsg = expected != null ? expected.toString() : "null";
		String actualMsg = actual != null ? actual.toString() : "null";
		return String.format("Expected:\t%s\nActual:\t\t%s\n", expectedMsg, actualMsg);
	}
}
