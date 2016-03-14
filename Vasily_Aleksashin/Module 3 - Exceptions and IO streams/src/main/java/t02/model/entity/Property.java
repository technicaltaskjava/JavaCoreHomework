package t02.model.entity;

import t02.exception.PropertyException;

public class Property {
	private final String key;
	private String value;

	public Property(final String key) throws PropertyException {
		if (key == null || key.equals("")) {
			throw new PropertyException("Key can not be NULL or EMPTY");
		}
		this.key = key;
	}

	public Property(final String key, final String value) throws PropertyException {
		this(key);
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Property property = (Property) o;

		return key.equals(property.key);

	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	@Override
	public String toString() {
		return "Property{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
