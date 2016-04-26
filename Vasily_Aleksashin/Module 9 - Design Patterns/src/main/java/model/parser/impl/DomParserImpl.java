package model.parser.impl;

import model.exception.XMLParseException;
import model.parser.FormField;
import model.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DomParserImpl implements Parser {

	@Override
	public Map<FormField, String> parse(final String xmlFileName) throws XMLParseException {
		Map<FormField, String> parsResult = new HashMap<>();
		final Document document = getDocument(xmlFileName);
		document.getDocumentElement().normalize();
		final NodeList rootNode = document.getElementsByTagName("USER");
		for (int index = 0; index < rootNode.getLength(); index++) {
			final Node node = rootNode.item(index);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				parsResult.put(FormField.FIRSTNAME, element.getElementsByTagName(FormField.FIRSTNAME.toString()).item(0).getTextContent());
				parsResult.put(FormField.LASTNAME, element.getElementsByTagName(FormField.LASTNAME.toString()).item(0).getTextContent());
				parsResult.put(FormField.USERNAME, element.getElementsByTagName(FormField.USERNAME.toString()).item(0).getTextContent());
				parsResult.put(FormField.EMAIL, element.getElementsByTagName(FormField.EMAIL.toString()).item(0).getTextContent());
				parsResult.put(FormField.ADDRESS, element.getElementsByTagName(FormField.ADDRESS.toString()).item(0).getTextContent());
				parsResult.put(FormField.LANGUAGE, element.getElementsByTagName(FormField.LANGUAGE.toString()).item(0).getTextContent());
			}
		}
		return parsResult;
	}

	private Document getDocument(final String xmlFileName) throws XMLParseException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
}
