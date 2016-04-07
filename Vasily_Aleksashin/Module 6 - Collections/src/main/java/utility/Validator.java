package utility;

import exception.ParameterValidateException;

public class Validator {
	private Validator() {
	}

	public static void isNull(final Object parameter, final String message) throws ParameterValidateException {
		if (parameter == null) {
			throw new ParameterValidateException(String.format("%s can not be NULL", message));
		}
	}

	public static void isPositive(final int parameter, final String message) throws ParameterValidateException {
		if (parameter < 0) {
			throw new ParameterValidateException(String.format("%s must not be is negative", message));
		}
	}

	public static void isInBoundary(final int position, final int length, final String message) throws ParameterValidateException {
		if (position < 0 || position >= length) {
			throw new ParameterValidateException(String.format("%s out of range from '0' to '%s'", message, length));
		}
	}
}
