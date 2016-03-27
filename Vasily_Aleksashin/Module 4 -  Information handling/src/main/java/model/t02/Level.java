package model.t02;

public enum Level {
	DEBUG("DEBUG", 1),
	INFO("INFO", 2),
	ERROR("ERROR", 3);

	private final String message;
	private final int level;

	Level(String message, int level) {
		this.message = message;
		this.level = level;
	}

	public static Level getLevel(final int level) {
		for (Level element : Level.values()) {
			if (element.getLevel() == level) {
				return element;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return message;
	}

	public int getLevel() {
		return level;
	}
}
