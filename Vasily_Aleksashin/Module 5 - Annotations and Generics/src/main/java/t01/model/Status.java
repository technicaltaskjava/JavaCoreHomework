package t01.model;

public enum Status {
	PASSED("passed"),
	FAILED("failed"),
	IGNORED("ignored");

	final String message;

	Status(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
