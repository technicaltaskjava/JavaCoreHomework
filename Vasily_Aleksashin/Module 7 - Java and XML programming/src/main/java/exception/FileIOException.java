package exception;

public class FileIOException extends Exception {
	public FileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIOException(String message) {
		super(message);
	}
}
