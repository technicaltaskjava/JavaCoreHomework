package model.conf;

import org.junit.Test;

import java.security.InvalidKeyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {
	private final Configuration configuration = Configuration.getInstance();

	@Test
	public void testGetInstance() {
		assertTrue(configuration == Configuration.getInstance());
	}

	@Test
	public void testGetProperty() throws InvalidKeyException {
		configuration.load("src/test/resources/configuration_test.properties");
		final String value = configuration.getProperty("db.cp");
		assertEquals("5", value);
	}

	@Test(expected = InvalidKeyException.class)
	public void testGetPropertyNotExistKey() throws InvalidKeyException {
		configuration.getProperty("keyNotExist");
	}
}