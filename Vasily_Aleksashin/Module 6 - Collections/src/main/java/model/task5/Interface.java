package model.task5;

public enum Interface {
	SET("Set"),
	LIST("List"),
	MAP("Map"),
	QUEUE("Queue");

	private final String name;

	Interface(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
