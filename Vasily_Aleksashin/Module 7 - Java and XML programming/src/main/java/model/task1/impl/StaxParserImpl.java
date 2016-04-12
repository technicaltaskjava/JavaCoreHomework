package model.task1.impl;

import exception.ParameterIsNullException;
import exception.XMLParseException;
import model.task1.Parser;
import model.task1.enums.TagName;
import utility.Constant;
import utility.Validator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaxParserImpl implements Parser {
	private final Map<String, ArrayList<String>> persons = new HashMap<>();
	private boolean hasSpeech;
	private String value;
	private String key;
	private List<String> tempPhrase;

	@Override
	public Map<String, ArrayList<String>> parse(final String xmlFileName) throws XMLParseException {
		try {
			Validator.isNull(xmlFileName);
		} catch (ParameterIsNullException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(xmlFileName));
			while (reader.hasNext()) {
				final int next = reader.next();
				switch (next) {
					case XMLStreamConstants.START_ELEMENT:
						isSpeech(reader);
						break;
					case XMLStreamConstants.END_ELEMENT:
						preparedPerson(reader);
						isSpeaker(reader);
						isLine(reader);
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
		return persons;
	}

	private void preparedPerson(final XMLStreamReader reader) {
		if (TagName.SPEECH.toString().equalsIgnoreCase(reader.getLocalName())) {
			hasSpeech = false;
			StringBuilder builder = new StringBuilder();
			for (String element : tempPhrase) {
				builder.append(element).append(" ");
			}
			addPerson(builder);
		}
	}

	private void addPerson(final StringBuilder builder) {
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

	private void isLine(final XMLStreamReader reader) {
		if (TagName.LINE.toString().equalsIgnoreCase(reader.getLocalName()) && hasSpeech) {
			tempPhrase.add(value);
		}
	}

	private void isSpeaker(final XMLStreamReader reader) {
		if (TagName.SPEAKER.toString().equalsIgnoreCase(reader.getLocalName()) && hasSpeech) {
			key = value;
		}
	}

	private void isSpeech(final XMLStreamReader reader) {
		if (TagName.SPEECH.toString().equalsIgnoreCase(reader.getLocalName())) {
			hasSpeech = true;
			tempPhrase = new ArrayList<>();
		}
	}
}
