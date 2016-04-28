package model.parser.impl;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DomParserImplTest {
	@Test
	public void testParseDom() throws XMLParseException {
		Parser parser = new DomParserImpl();
		final Map<FormField, String> userForm = parser.parse("src/test/resources/test.xml");
		assertTrue(userForm.size() == 6);
	}
	
}