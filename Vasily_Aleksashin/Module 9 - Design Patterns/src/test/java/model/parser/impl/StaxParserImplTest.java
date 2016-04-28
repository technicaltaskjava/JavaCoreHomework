package model.parser.impl;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StaxParserImplTest {
	@Test
	public void testParse() throws XMLParseException {
		Parser parser = new StaxParserImpl();
		final Map<FormField, String> userForm = parser.parse("src/test/resources/test.xml");
		assertTrue(userForm.size() == 6);
	}
	
}