package model.task5;

import exception.FileIOException;
import exception.ParameterValidateException;
import utility.Validator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Description {
	private static final Properties properties = new Properties();
	private static Map<String, String> descriptionMap = null;

	private Description() {
	}

	@SuppressWarnings("SameParameterValue")
	public static Map<String, String> getDescription(final String fileName) throws FileIOException {
		File file = null;
		try {
			Validator.isNull(fileName, "File name");
			file = new File(fileName);
			if (!file.canRead()) {
				throw new FileIOException(String.format("Can not read file '%s'", file));
			}
			loadDescription(file);
		} catch (IOException e) {
			throw new FileIOException(String.format("Can not load descriptionMap from file '%s'", file), e);
		} catch (ParameterValidateException e) {
			throw new FileIOException(e.getMessage(), e);
		}
		return descriptionMap;
	}

	private static void loadDescription(final File file) throws IOException {
		try (FileReader reader = new FileReader(file)) {
			properties.load(reader);
			descriptionMap = new HashMap<>();
			Set<Object> keySet = properties.keySet();
			for (Object o : keySet) {
				String key = (String) o;
				descriptionMap.put(key, properties.getProperty(key));
			}
		}
	}
}
