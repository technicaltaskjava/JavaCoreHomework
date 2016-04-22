package utility;

import exception.ParameterIncorrectException;

public class Validator {
	private Validator() {
	}

	public static void isNull(final Object object) throws ParameterIncorrectException {
		if (object == null) {
			throw new ParameterIncorrectException("Parameter can not be NULL");
		}
	}

	public static void isPositive(final int number) throws ParameterIncorrectException {
		if (number < 1) {
			throw new ParameterIncorrectException("Parameter must be positive");
		}
	}

	public static void isNotNegative(final int number) throws ParameterIncorrectException {
		if (number < 0) {
			throw new ParameterIncorrectException("Parameter must be positive");
		}
	}
}
