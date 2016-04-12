package model.task1.impl;

import exception.XMLParseException;
import model.task1.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DomParserImplTest {
	private static final String XML_FILE = "src/test/resources/test.xml";
	private static final String XML_FILE_SIMPLE = "src/test/resources/simple_test.xml";
	private final Parser parser = new DomParserImpl();
	private Map<String, ArrayList<String>> persons;

	@Test
	public void testDomParseSimple() throws XMLParseException {
		persons = parser.parse(XML_FILE_SIMPLE);
		final String expected = "{ADAM=[Yonder comes my master, your brother. Yonder comes my master, your brother. ]}";
		final String actual = persons.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testDomParseLarge() throws XMLParseException {
		persons = parser.parse(XML_FILE);
		assertTrue(persons.size() == 27);
	}

	@Test(expected = XMLParseException.class)
	public void testDomParseNull() throws XMLParseException {
		parser.parse(null);
	}
	
	@Test(expected = XMLParseException.class)
	public void testDomParserNotExistedFile() throws XMLParseException {
		parser.parse("");
	}
}