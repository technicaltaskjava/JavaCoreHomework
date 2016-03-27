package util;

import exeption.ParameterIsNullException;
import model.t02.CrazyLogger;

public class Validator {
	private static CrazyLogger logger = CrazyLogger.getLogger(Validator.class);

	public static void validate(final Object parameter) throws ParameterIsNullException {
		if (parameter == null || parameter.equals("")) {
			logger.log(3, "Parameter incorrect");
			throw new ParameterIsNullException("Parameter incorrect");
		}
	}
}
