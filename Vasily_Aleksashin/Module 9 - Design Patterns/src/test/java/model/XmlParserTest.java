package model;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.impl.DomParserImpl;
import model.parser.impl.SaxParserImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlParserTest {
	private XmlParser parser;

	@Before
	public void setUp() {
		parser = new XmlParser(new DomParserImpl());
	}

	@Test
	public void testSetParser() {
		parser.setParser(new SaxParserImpl());
		assertEquals("SaxParserImpl", parser.getParserName());
	}

	@Test
	public void testParse() throws XMLParseException {
		final Map<FormField, String> userForm = parser.parse("src/test/resources/test.xml");
		assertTrue(userForm.size() == 6);
	}

	@Test
	public void testGetParserName() {
		assertEquals("DomParserImpl", parser.getParserName());
	}

	@Test(expected = XMLParseException.class)
	public void testParseNotExistFile() throws XMLParseException {
		parser.parse("src/test/resources/not_exist.xml");
	}
}