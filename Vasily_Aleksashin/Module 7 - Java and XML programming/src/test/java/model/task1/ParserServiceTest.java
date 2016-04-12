package model.task1;

import exception.XMLParseException;
import model.task1.enums.ParserType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ParserServiceTest {
	private static final String XML_FILE_SIMPLE = "src/test/resources/simple_test.xml";
	private static final String PERSONA = "ADAM";

	private ParserService service;

	@Before
	public void setUp() throws XMLParseException {
		service = ParserService.getInstance(ParserType.DOM);
		service.loadXML(XML_FILE_SIMPLE);
	}

	@Test(expected = XMLParseException.class)
	public void testLoadXMLIsNull() throws XMLParseException {
		service.loadXML(null);
	}

	@Test
	public void testGetPersons() throws XMLParseException {
		final Set<String> actual = service.getPersons();
		final String expected = "[" + PERSONA + "]";
		assertEquals(expected, actual.toString());
	}

	@Test
	public void testGetPhrase() throws XMLParseException {
		final List<String> phrase = service.getPhrase(PERSONA);
		final String expected = "[Yonder comes my master, your brother. Yonder comes my master, your brother. ]";
		assertEquals(expected, phrase.toString());
	}

	@Test
	public void testGetCount() {
		final int actual = service.getCount(PERSONA);
		final int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testGatAverage() {
		final int actual = service.gatAverage(PERSONA);
		final int expected = 12;
		assertEquals(expected, actual);
	}
	
}