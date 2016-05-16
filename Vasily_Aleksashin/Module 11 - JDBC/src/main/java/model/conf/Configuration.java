package model.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Properties;

public class Configuration {
	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static final Configuration instance = new Configuration();
	private static final String DEFAULT_CONFIGURATION_FILE = "src/main/resources/configuration.properties";

	private Properties properties = null;

	private Configuration() {
		load(null);
	}

	public static Configuration getInstance() {
		return instance;
	}

	public void load(final String path) {
		String file = DEFAULT_CONFIGURATION_FILE;
		if (path != null) {
			file = path;
		}
		properties = new Properties();
		try (FileInputStream stream = new FileInputStream(file)) {
			properties.load(stream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public synchronized String getProperty(final String key) throws InvalidKeyException {
		String value;
		if (properties.containsKey(key)) {
			value = properties.getProperty(key);
		} else {
			throw new InvalidKeyException(String.format("Kay '%s' not found", key));
		}
		return value;
	}
}