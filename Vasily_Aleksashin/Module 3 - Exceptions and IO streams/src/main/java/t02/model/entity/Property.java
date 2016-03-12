package t02.model.entity;

import t02.exception.ParameterNullException;

public class Property {
	private final String key;
	private String value;

	public Property(final String key) throws ParameterNullException {
		if (key == null || key.equals("")) {
			throw new ParameterNullException("Key can not be NULL or EMPTY");
		}
		this.key = key;
	}

	public Property(final String key, final String value) throws ParameterNullException {
		this(key);
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) throws ParameterNullException {
		if (value == null) {
			throw new ParameterNullException("Value can not be NULL");
		}
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
