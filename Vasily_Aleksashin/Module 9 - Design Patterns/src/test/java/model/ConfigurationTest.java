package model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;

import static org.junit.Assert.*;

public class ConfigurationTest {
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);
	private static final String FILE_NAME = "src/test/resources/configuration.bin";

	private final Configuration configuration = Configuration.getInstance();

	@Test
	public void testGetInstance() {
		assertTrue(configuration == Configuration.getInstance());
	}

	@Test
	public void testGetProperty() throws InvalidKeyException {
		final String value = configuration.getProperty("key");
		assertEquals("value", value);
	}

	@Test(expected = InvalidKeyException.class)
	public void testGetPropertyNotExistKey() throws InvalidKeyException {
		configuration.getProperty("keyNotExist");
	}

	@Test(expected = CloneNotSupportedException.class)
	public void testClone() throws CloneNotSupportedException {
		configuration.clone();
	}

	@Test
	public void testSerialize() {
		try (FileOutputStream out = new FileOutputStream(FILE_NAME);
		     ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
			outputStream.writeObject(configuration);
			outputStream.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		try (FileInputStream in = new FileInputStream(FILE_NAME);
		     ObjectInputStream inputStream = new ObjectInputStream(in)) {
			assertTrue(configuration == inputStream.readObject());
		} catch (ClassNotFoundException | IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Test(expected = InvocationTargetException.class)
	public void testReflation() throws InvocationTargetException {
		final Constructor<?>[] constructors = Configuration.class.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true);
			try {
				constructor.newInstance();
				fail();
			} catch (InvocationTargetException e) {
				assertEquals("Configuration already exist", e.getCause().getMessage());
				throw e;
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}
}