package model.entity.enums;

public enum Rating {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	UNRATED(0);

	private static final String STAR = "\u2605";
	private final int rating;

	Rating(final int rating) {
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}

	@Override
	public String toString() {
		if (rating == 0) {
			return "unrated";
		}

		StringBuilder builder = new StringBuilder();
		int index = 1;
		while (index++ <= rating) {
			builder.append(STAR);
		}

		return builder.toString();
	}

	public static Rating getRating(final Integer rating) {
		if (rating == null) {
			return null;
		}
		for (Rating element : Rating.values()) {
			if (element.getRating() == rating) {
				return element;
			}
		}
		return null;
	}
}
