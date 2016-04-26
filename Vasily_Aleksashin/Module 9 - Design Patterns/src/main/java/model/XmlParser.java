package model;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;

import java.util.Map;

public class XmlParser {
	private Parser parser;

	public XmlParser(final Parser parser) {
		this.parser = parser;
	}

	public void setParser(final Parser parser) {
		this.parser = parser;
	}

	public Map<FormField, String> parse(final String fileName) throws XMLParseException {
		return parser.parse(fileName);
	}

	public String getParserName() {
		return parser.getClass().getSimpleName();
	}
}
