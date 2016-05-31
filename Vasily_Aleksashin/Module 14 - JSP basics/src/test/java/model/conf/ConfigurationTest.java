package model.conf;

import exception.ConfigurationLoadException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ConfigurationTest {
	private static final String CONFIGURATION_TEST_PROPERTIES = "configuration_test.properties";
	private Configuration configuration;

	@Mock
	private ServletContext contextMock;

	@Before
	public void setUp() throws MalformedURLException {
		MockitoAnnotations.initMocks(this);
		URL resource = ClassLoader.getSystemResource(CONFIGURATION_TEST_PROPERTIES);
		when(contextMock.getResource(CONFIGURATION_TEST_PROPERTIES)).thenReturn(resource);
		configuration = Configuration.getInstance(contextMock);
	}

	@Test
	public void testGetInstance() {
		assertTrue(configuration == Configuration.getInstance());
	}

	@Test
	public void testGetProperty() throws InvalidKeyException, ConfigurationLoadException {
		configuration.load(CONFIGURATION_TEST_PROPERTIES);
		final String value = configuration.getProperty("db.cp");
		assertEquals("5", value);
	}

	@Test(expected = InvalidKeyException.class)
	public void testGetPropertyNotExistKey() throws InvalidKeyException {
		configuration.getProperty("keyNotExist");
	}
}