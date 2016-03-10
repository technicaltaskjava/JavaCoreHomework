package t01.exception;

public class ModelException extends Exception {
	public ModelException(final String message) {
		super(message);
	}

	public ModelException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
