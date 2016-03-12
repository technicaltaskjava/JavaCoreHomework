package t02.model;

import t02.exception.ParameterNullException;
import t02.exception.PropertyNotFoundException;
import t02.model.entity.Property;

import java.util.Arrays;

public class PropertiesImpl implements Properties {
	private Property[] properties;

	@Override
	public Property[] getProperties() {
		return properties != null ? Arrays.copyOf(properties, properties.length) : properties;
	}

	@Override
	public String getValue(final String key) throws ParameterNullException, PropertyNotFoundException {
		validate(key);
		for (Property property : properties) {
			if (property != null & property.getKey().equals(key)) {
				return property.getValue();
			}
		}
		throw new PropertyNotFoundException("Property not found");
	}

	@Override
	public void add(final Property property) throws ParameterNullException {
		try {
			update(property);
		} catch (PropertyNotFoundException e) {
			if (properties == null) {
				properties = new Property[1];
				properties[0] = property;
			} else {
				boolean unique = true;
				boolean nullable = false;
				for (int index = 0; index < properties.length; index++) {
					if (properties[index] != null) {
						if (properties[index].equals(property)) {
							unique = false;
							properties[index] = property;
							break;
						}
					} else {
						nullable = true;
					}
				}
				if (unique) {
					if (nullable) {
						for (int index = 0; index < properties.length; index++) {
							if (properties[index] == null) {
								properties[index] = property;
								break;
							}
						}
					} else {
						properties = Arrays.copyOf(properties, properties.length + 1);
						properties[properties.length - 1] = property;
					}
				}
			}
		}
	}

	@Override
	public void remove(final Property property) throws ParameterNullException, PropertyNotFoundException {
		validate(property);
		if (properties == null) {
			throw new PropertyNotFoundException("Property not found");
		}
		boolean success = false;
		for (int index = 0; index < properties.length; index++) {
			if (property.equals(properties[index])) {
				properties[index] = null;
				success = true;
				break;
			}
		}
		successOperation(success);
	}

	@Override
	public void update(final Property property) throws ParameterNullException, PropertyNotFoundException {
		validate(property);
		if (properties == null) {
			throw new PropertyNotFoundException("Property not found");
		}
		boolean success = false;
		for (int index = 0; index < properties.length; index++) {
			if (property.equals(properties[index])) {
				properties[index] = property;
				break;
			}
		}
		successOperation(success);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (properties != null) {
			for (Property prop : properties) {
				builder.append("\n").append(prop.toString());
			}
			return "Properties{" +
					builder.toString() +
					"\n}";
		}
		return "Properties{ EMPTY }";
	}

	private void validate(final Object property) throws ParameterNullException {
		if (property == null) {
			throw new ParameterNullException("Property can not be NULL");
		}
	}

	private void successOperation(final boolean success) throws PropertyNotFoundException {
		if (!success) {
			throw new PropertyNotFoundException("Property not found");
		}
	}
}
