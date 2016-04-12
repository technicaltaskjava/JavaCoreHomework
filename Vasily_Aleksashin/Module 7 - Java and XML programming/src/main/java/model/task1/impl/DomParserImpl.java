package model.task1.impl;

import exception.ParameterIsNullException;
import exception.XMLParseException;
import model.task1.Parser;
import model.task1.enums.TagName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utility.Constant;
import utility.Validator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DomParserImpl implements Parser {
	@Override
	public Map<String, ArrayList<String>> parse(final String xmlFileName) throws XMLParseException {
		try {
			Validator.isNull(xmlFileName);
		} catch (ParameterIsNullException e) {
			throw new XMLParseException(e.getMessage(), e);
		}

		final Document document = getDocument(xmlFileName);
		document.getDocumentElement().normalize();

		final String tagNameSpeech = TagName.SPEECH.toString();
		final NodeList speechList = document.getElementsByTagName(tagNameSpeech);
		Map<String, ArrayList<String>> persons = new HashMap<>();

		for (int index = 0; index < speechList.getLength(); index++) {
			final Node node = speechList.item(index);
			final String key = getKey((Element) node);
			final String value = getValue((Element) node);
			if (persons.containsKey(key)) {
				final ArrayList<String> oldValue = persons.get(key);
				oldValue.add(value);
				persons.put(key, oldValue);
			} else {
				final ArrayList<String> newValue = new ArrayList<>();
				newValue.add(value);
				persons.put(key, newValue);
			}
		}
		return persons;
	}

	private Document getDocument(final String xmlFileName) throws XMLParseException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);
			factory.setFeature("http://xml.org/sax/features/namespaces", false);
			factory.setFeature("http://xml.org/sax/features/validation", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			final File file = new File(xmlFileName);
			if (!file.canRead()) {
				throw new IOException(String.format("Can not read file '%s'", file.getAbsolutePath()));
			}
			return builder.parse(file);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new XMLParseException(e.getMessage(), e);
		}
	}

	private String getKey(final Element element) {
		final String tagNameSpeaker = TagName.SPEAKER.toString();
		return element.getElementsByTagName(tagNameSpeaker).item(0).getTextContent();
	}

	private String getValue(final Element element) {
		final String tagNameLine = TagName.LINE.toString();
		final NodeList lines = element.getElementsByTagName(tagNameLine);
		final StringBuilder builder = new StringBuilder();
		for (int lineIndex = 0; lineIndex < lines.getLength(); lineIndex++) {
			Node node = lines.item(lineIndex);
			final String textContent = node.getTextContent();
			builder.append(textContent).append(" ");
		}
		return builder.toString().replaceAll(Constant.REGEX_WHITESPACE, Constant.REPLACEMENT);
	}
}
