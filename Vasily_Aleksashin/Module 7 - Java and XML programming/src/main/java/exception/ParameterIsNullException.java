package exception;

public class ParameterIsNullException extends Exception {
	public ParameterIsNullException() {
		super("Parameter can not be NULL");
	}
}
