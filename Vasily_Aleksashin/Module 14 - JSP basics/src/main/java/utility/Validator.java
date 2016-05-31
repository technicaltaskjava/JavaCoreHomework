package utility;

public class Validator {
	private Validator() {
	}

	public static void isNull(final Object object, final String message) {
		if (object == null) {
			throw new IllegalArgumentException(String.format("%s can not be NULL", message));
		}
	}

	public static void isNull(final Object object) {
		isNull(object, "Parameter");
	}
}
