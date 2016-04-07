package exception;

public class ExitException extends Exception {
	public ExitException() {
		super("Thank you for using my app");
	}

	public ExitException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
