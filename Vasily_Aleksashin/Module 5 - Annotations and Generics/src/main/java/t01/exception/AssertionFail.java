package t01.exception;

public class AssertionFail extends Error {
	public AssertionFail(final String message) {
		super(message);
	}
}
