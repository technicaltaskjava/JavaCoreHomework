package exception;

public class FileIOException extends Exception {
	public FileIOException(final String message) {
		super(message);
	}

	public FileIOException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
