package exeption;

public class ExitException extends Exception {
	public ExitException(final String message) {
		super(message);
	}

	public ExitException() {
		super();
	}
}
