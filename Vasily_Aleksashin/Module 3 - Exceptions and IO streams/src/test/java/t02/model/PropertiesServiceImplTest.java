package t02.model;

import org.junit.Before;
import org.junit.Test;
import t01.model.file.impl.FileOperationsImpl;
import t02.exception.PropertyException;
import t02.model.entity.Property;

import java.io.File;

import static org.junit.Assert.*;

public class PropertiesServiceImplTest {
	private static final String KEY = "testKey";
	private static final String VALUE = "testValue";
	private String fileProperties = "test.properties";
	private PropertiesService service;

	@Before
	public void setUp() throws Exception {
		service = new PropertiesServiceImpl(new FileOperationsImpl());
	}

	@Test
	public void testLoadCorrectFile() throws PropertyException {
		service.load(convertPath(fileProperties));
		assertNotNull(service.getProperties());
	}

	@Test(expected = PropertyException.class)
	public void testLoadFileNotExist() throws PropertyException {
		service.load(convertPath("wrong.properties"));
	}

	@Test(expected = PropertyException.class)
	public void testLoadFileIsNull() throws PropertyException {
		service.load(null);
	}

	@Test(expected = PropertyException.class)
	public void testLoadFileIsNotProperty() throws PropertyException {
		service.load("test.txt");
	}

	@Test(expected = PropertyException.class)
	public void testGetValueByKeyIsNull() throws PropertyException {
		service.load(convertPath(fileProperties));
		service.getValueByKey(null);
	}

	@Test(expected = PropertyException.class)
	public void testGetValueByKeyIsWrong() throws PropertyException {
		service.load(convertPath(fileProperties));
		service.getValueByKey("wrong");
	}

	@Test
	public void testGetValueByKeyIsCorrect() throws PropertyException {
		service.load(convertPath(fileProperties));
		assertNotNull(service.getValueByKey("key2"));
	}

	@Test
	public void testAddCorrectKey() throws PropertyException {
		service.add(KEY, VALUE);
		assertNotNull(service.getProperties());
	}

	@Test
	public void testAddTwice() throws PropertyException {
		service.add(KEY, VALUE);
		service.add("newKey", VALUE);
		int firstAdded = service.getProperties().length;
		service.remove("newKey");
		service.add("newKey", VALUE);
		int secondAdded = service.getProperties().length;
		assertEquals(firstAdded, secondAdded);
	}

	@Test(expected = PropertyException.class)
	public void testAddKeyIsNull() throws PropertyException {
		service.add(null, VALUE);
	}

	@Test(expected = PropertyException.class)
	public void testAddPropertyIsNull() throws PropertyException {
		service.add(null);
	}

	@Test
	public void testAddProperty() throws PropertyException {
		service.add(new Property(KEY, VALUE));
		assertNotNull(service.getProperties());
	}

	@Test
	public void testUpdateCorrectKey() throws PropertyException {
		service.add(KEY, VALUE);
		String expected = "new test value";
		service.update(KEY, expected);
		assertEquals(expected, service.getValueByKey(KEY));
	}

	@Test(expected = PropertyException.class)
	public void testUpdateKeyIsNull() throws PropertyException {
		service.update(null, VALUE);
	}

	@Test(expected = PropertyException.class)
	public void testUpdatePropertyIsNull() throws PropertyException {
		service.update(null);
	}

	@Test
	public void testUpdateProperty() throws PropertyException {
		service.add(KEY, VALUE);
		String expected = "new test value";
		service.update(new Property(KEY, expected));
		assertEquals(expected, service.getValueByKey(KEY));
	}

	@Test(expected = PropertyException.class)
	public void testRemoveCorrectKey() throws PropertyException {
		service.add(KEY, VALUE);
		service.remove(KEY);
		service.getValueByKey(KEY);
	}

	@Test(expected = PropertyException.class)
	public void testRemoveKeyIsNull() throws PropertyException {
		String key = null;
		service.remove(key);
	}

	@Test(expected = PropertyException.class)
	public void testRemovePropertyIsNull() throws PropertyException {
		Property property = null;
		service.remove(property);
	}

	@Test(expected = PropertyException.class)
	public void testRemoveProperty() throws PropertyException {
		service.add(KEY, VALUE);
		Property property = new Property(KEY);
		service.remove(property);
		service.getValueByKey(KEY);
	}

	@Test
	public void testGetSeparator() throws Exception {
		assertEquals("=", service.getSeparator());
	}

	@Test
	public void testGetPropertiesIsNull() throws Exception {
		assertNull(service.getProperties());
	}

	@Test
	public void testToString() throws Exception {
		service.add(KEY, VALUE);
		String expected = "Properties{\n" +
				"Property{key='testKey', value='testValue'}\n" +
				"}";
		assertEquals(expected, service.toString());
	}

	@Test
	public void testToStringEmptyProperties() throws Exception {
		String expected = "Properties{ EMPTY }";
		assertEquals(expected, service.toString());
	}

	private String convertPath(final String fileProperties) {
		StringBuilder builder = new StringBuilder();
		builder
				.append(System.getProperty("user.dir"))
				.append(File.separator)
				.append("src")
				.append(File.separator)
				.append("test")
				.append(File.separator)
				.append("resources")
				.append(File.separator)
				.append(fileProperties);
		return builder.toString();
	}
}