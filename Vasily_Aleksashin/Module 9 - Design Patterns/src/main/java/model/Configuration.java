package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.util.Properties;

public class Configuration implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static final long serialVersionUID = 1L;
	private static final Configuration instance = new Configuration();

	private Properties properties = null;

	/**
	 * An "if condition" inside the constructor can prevent the singleton from getting
	 * instantiated more than once using reflection
	 *
	 * @throws IllegalStateException
	 */
	private Configuration() {
		if (instance != null) {
			throw new IllegalStateException("Configuration already exist");
		}
		properties = new Properties();
		try (FileInputStream stream = new FileInputStream("src/main/resources/configuration.properties")) {
			properties.load(stream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static Configuration getInstance() {
		return instance;
	}

	/**
	 * Implement the getClass() method to prevent the singleton getting instantiated from different class loaders.
	 * The above getClass() method associates the classloader with the current thread; if that classloader is null,
	 * the method uses the same classloader that loaded the singleton class.
	 *
	 * @param clazz class name
	 * @return ClassLoader
	 * @throws ClassNotFoundException
	 */
	private static Class getClass(final String clazz) throws ClassNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = Configuration.class.getClassLoader();
		}
		return loader.loadClass(clazz);
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

	/**
	 * Implement the clone() method and throw an exception so that the singleton cannot be cloned
	 *
	 * @throws CloneNotSupportedException
	 */
	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public Object clone() throws CloneNotSupportedException { //NOSONAR This method protects against cloning
		throw new CloneNotSupportedException("Configuration can not be cloned");
	}

	/**
	 * Implement the readResolve() methods in singleton class and return the same object
	 *
	 * @return Configuration instance
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	/**
	 * Implement the writeReplace() methods in singleton class and return the same object
	 *
	 * @return Configuration instance
	 * @throws ObjectStreamException
	 */
	private Object writeReplace() throws ObjectStreamException {
		return instance;
	}
}
