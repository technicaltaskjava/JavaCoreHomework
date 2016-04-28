package model.parser.impl;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class StaxParserImpl implements Parser {
	private final Map<FormField, String> parsResult = new HashMap<>();
	private String value;

	@Override
	public Map<FormField, String> parse(final String xmlFileName) throws XMLParseException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(xmlFileName));
			while (reader.hasNext()) {
				final int next = reader.next();
				switch (next) {
					case XMLStreamConstants.END_ELEMENT:
						addMapEntry(reader);
						break;
					case XMLStreamConstants.CHARACTERS:
						value = reader.getText().trim();
						break;
					default:
						break;
				}
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
		return parsResult;
	}

	private void addMapEntry(final XMLStreamReader reader) {
		for (int index = 0; index < FormField.values().length; index++) {
			if (reader.getLocalName().equalsIgnoreCase(FormField.values()[index].toString())) {
				parsResult.put(FormField.values()[index], value);
				break;
			}
		}
	}
}
