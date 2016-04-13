package utility;

import exception.ParameterIsNullException;

public class Validator {
	private Validator() {
	}

	public static void isNull(final Object object) throws ParameterIsNullException {
		if (object == null) {
			throw new ParameterIsNullException();
		}
	}
}
