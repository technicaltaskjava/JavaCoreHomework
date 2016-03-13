package t02.model;

import t01.exception.ModelException;
import t01.model.file.FileOperations;
import t02.exception.PropertyException;
import t02.model.entity.Property;

import java.util.Arrays;
import java.util.Scanner;

public class PropertiesServiceImpl implements PropertiesService {
	private static final String FIE_EXTENSION = ".properties";

	private final FileOperations fileOperations;
	private Property[] properties;
	private String separator = "=";

	public PropertiesServiceImpl(final FileOperations fileOperations) {
		this.fileOperations = fileOperations;
	}

	@Override
	public void load(final String fileName) throws PropertyException {
		load(fileName, separator);
	}

	@Override
	public void load(final String fileName, final String separator) throws PropertyException {
		validate(fileName);
		if (!fileName.endsWith(FIE_EXTENSION)) {
			throw new PropertyException("File extension incorrect, expected *.properties");
		}
		if (separator != null) {
			this.separator = separator;
		}
		try {
			String loadFile = fileOperations.read(fileName);
			parsePropertyFile(loadFile);
		} catch (ModelException e) {
			String errorMsg = e.getCause().getClass().getSimpleName() + ":\n" + e.getMessage();
			throw new PropertyException(errorMsg);
		}
	}

	@Override
	public String getValueByKey(final String key) throws PropertyException {
		validate(key);
		for (Property property : properties) {
			if (property != null && property.getKey().equals(key)) {
				return property.getValue();
			}
		}
		throw new PropertyException(String.format("Key '%s' not found", key));
	}

	@Override
	public void add(final String key, final String value) throws PropertyException {
		add(new Property(key, value));
	}

	@Override
	public void add(final Property property) throws PropertyException {
		validate(property);
		try {
			update(property);
		} catch (PropertyException e) {
			if (properties == null) {
				properties = new Property[1];
				properties[0] = property;
			} else {
				boolean nullable = false;
				for (Property prop : properties) {
					if (prop != null) {
						if (prop.equals(property)) {
							throw new PropertyException(String.format("Property with key '%s' is exist", property.getKey()));
						}
					} else {
						nullable = true;
					}
				}
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

	@Override
	public void update(final String key, final String value) throws PropertyException {
		update(new Property(key, value));
	}

	@Override
	public void update(final Property property) throws PropertyException {
		validate(property);
		if (properties == null) {
			throw new PropertyException("Property not found");
		}
		boolean success = false;
		for (int index = 0; index < properties.length; index++) {
			if (property.equals(properties[index])) {
				properties[index] = property;
				success = true;
				break;
			}
		}
		successOperation(success);
	}

	@Override
	public void remove(final Property property) throws PropertyException {
		validate(property);
		remove(property.getKey());

	}

	@Override
	public void remove(final String key) throws PropertyException {
		validate(key);
		boolean success = false;
		for (int index = 0; index < properties.length; index++) {
			if (key.equals(properties[index].getKey())) {
				properties[index] = null;
				success = true;
				break;
			}
		}
		successOperation(success);
	}

	@Override
	public String getSeparator() {
		return separator;
	}

	@Override
	public Property[] getProperties() {
		return properties != null ? Arrays.copyOf(properties, properties.length) : null;
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

	private void parsePropertyFile(final String loadFile) {
		try (Scanner scanner = new Scanner(loadFile)) {
			String line;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String key;
				String value;
				if (line.contains(separator)) {
					key = line.substring(0, line.indexOf(separator));
					value = line.substring(line.indexOf(separator) + separator.length(), line.length());
					try {
						add(new Property(key, value));
					} catch (PropertyException e) {
						//can not parse line
					}
				}
			}
		}
	}

	private void validate(final Object obj) throws PropertyException {
		if (obj == null) {
			throw new PropertyException("Parameter can not be NULL");
		}
	}

	private void successOperation(final boolean success) throws PropertyException {
		if (!success) {
			throw new PropertyException("Property not found");
		}
	}
}
