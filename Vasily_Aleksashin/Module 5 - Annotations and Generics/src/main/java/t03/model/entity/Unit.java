package t03.model.entity;

import t03.model.Tuple;

public class Unit<A> implements Tuple {
	private final A value;

	private Unit(final A value) {
		this.value = value;
	}

	public static <A> Unit create(final A single) {
		return new Unit<>(single);
	}

	public A getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Unit<?> unit = (Unit<?>) o;

		return value.equals(unit.value);

	}

	@Override
	public String toString() {
		return "Unit{" +
				"value=" + value +
				'}';
	}
}
