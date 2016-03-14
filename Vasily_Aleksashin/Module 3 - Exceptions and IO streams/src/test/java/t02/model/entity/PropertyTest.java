package t02.model.entity;

import org.junit.Before;
import org.junit.Test;
import t02.exception.PropertyException;

import static org.junit.Assert.*;

public class PropertyTest {
	private static final String KEY = "key";
	private static final String VALUE = "value";
	private Property property;

	@Before
	public void setUp() throws PropertyException {
		property = new Property(KEY, VALUE);
	}

	@Test(expected = PropertyException.class)
	public void testConstructorKeyIsNull() throws PropertyException {
		new Property(null);
	}

	@Test
	public void testGetKey() {
		assertEquals(KEY, property.getKey());
	}

	@Test
	public void testGetValue() {
		assertEquals(VALUE, property.getValue());
	}

	@Test
	public void testSetValue() {
		String expected = "new value";
		property.setValue(expected);
		assertEquals(expected, property.getValue());
	}

	@Test
	public void testEqualsReflexive() {
		assertTrue(property.equals(property));
	}

	@Test
	public void testEqualsNewProperty() throws PropertyException {
		assertTrue(property.equals(new Property(KEY)));
	}

	@Test
	public void testEqualsNewPropertyDiffKey() throws PropertyException {
		assertFalse(property.equals(new Property("new key")));
	}

	@Test
	public void testEqualsNull() throws PropertyException {
		assertFalse(property.equals(null));
	}

	@Test
	public void testEqualsObject() throws PropertyException {
		assertFalse(property.equals(new Object()));
	}

	@Test
	public void testHashCode() {
		assertEquals(KEY.hashCode(), property.hashCode());
	}

	@Test
	public void testToString() {
		String expected = "Property{key='key', value='value'}";
		assertEquals(expected, property.toString());
	}
}