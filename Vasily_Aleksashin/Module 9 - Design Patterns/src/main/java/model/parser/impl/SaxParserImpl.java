package model.parser.impl;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaxParserImpl extends DefaultHandler implements Parser {
	private final Map<FormField, String> parsResult = new HashMap<>();
	private String value;

	@Override
	public Map<FormField, String> parse(final String xmlFileName) throws XMLParseException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(xmlFileName, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
		return parsResult;
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		for (int index = 0; index < FormField.values().length; index++) {
			if (qName.equalsIgnoreCase(FormField.values()[index].toString())) {
				parsResult.put(FormField.values()[index], value);
				break;
			}
		}
	}

	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException {
		value = new String(ch, start, length);
	}
}
