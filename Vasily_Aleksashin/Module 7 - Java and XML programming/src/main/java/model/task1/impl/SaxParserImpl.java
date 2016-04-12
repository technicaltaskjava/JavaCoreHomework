package model.task1.impl;

import exception.ParameterIsNullException;
import exception.XMLParseException;
import model.task1.Parser;
import model.task1.enums.TagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utility.Constant;
import utility.Validator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaxParserImpl extends DefaultHandler implements Parser {
	private final Map<String, ArrayList<String>> persons = new HashMap<>();
	private boolean isSpeech;
	private String tempValue;
	private String key;
	private List<String> tempPhrase;

	@Override
	public Map<String, ArrayList<String>> parse(final String xmlFileName) throws XMLParseException {
		try {
			Validator.isNull(xmlFileName);
		} catch (ParameterIsNullException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(xmlFileName, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
		return persons;
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase(TagName.SPEECH.toString())) {
			isSpeech = true;
			tempPhrase = new ArrayList<>();
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		if (qName.equalsIgnoreCase(TagName.SPEECH.toString())) {
			isSpeech = false;
			StringBuilder builder = new StringBuilder();
			for (String element : tempPhrase) {
				builder.append(element).append(" ");
			}
			final String phraseValue = builder.toString().replaceAll(Constant.REGEX_WHITESPACE, Constant.REPLACEMENT);
			if (persons.containsKey(key)) {
				final ArrayList<String> oldValue = persons.get(key);
				oldValue.add(phraseValue);
				persons.put(key, oldValue);
			} else {
				final ArrayList<String> newValue = new ArrayList<>();
				newValue.add(phraseValue);
				persons.put(key, newValue);
			}
		}
		if (qName.equalsIgnoreCase(TagName.SPEAKER.toString()) && isSpeech) {
			key = tempValue;
		}
		if (qName.equalsIgnoreCase(TagName.LINE.toString()) && isSpeech) {
			tempPhrase.add(tempValue);
		}
	}

	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException {
		tempValue = new String(ch, start, length);
	}
}
