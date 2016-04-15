package com.epam.task1.dom;

import com.epam.task1.type.TagName;
import com.epam.task1.exception.XMLParseException;
import com.epam.task1.model.Speech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 09-Apr-16.
 */
public class DOMPlayParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DOMPlayParser.class);
    private static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    private DOMPlayParser() {
    }

    private static Element getDocumentElement(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            dbf.setFeature(LOAD_EXTERNAL_DTD, false);
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new XMLParseException(e.getMessage(), e.getCause());
        }
        
        Document document;
        try {
            document = db.parse(inputStream);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new XMLParseException(e.getMessage(), e.getCause());
        }
        return document.getDocumentElement();
    }

    private static Speech getSpeech(Element speechElement) {
        Speech speech = new Speech();
        speech.setSpeaker(getSpeakerName(speechElement));
        speech.setCue(getCueText(speechElement));
        return speech;
    }

    private static String getSpeakerName(Element element) {
        NodeList nodeList = element.getElementsByTagName(String.valueOf(TagName.SPEAKER));
        Element child = (Element) nodeList.item(0);
        return child.getTextContent().trim();
    }

    private static String getCueText(Element element) {
        NodeList nodeList = element.getElementsByTagName(String.valueOf(TagName.LINE));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element line = (Element) nodeList.item(i);
            sb.append(line.getTextContent().trim());
            sb.append('\n');
        }
        return sb.toString();
    }

    public static List<Speech> getSpeechesFromXML(InputStream inputStream) {
        Element play = getDocumentElement(inputStream);
        List<Speech> speeches = new ArrayList<>();
        NodeList speechNodes = play.getElementsByTagName(String.valueOf(TagName.SPEECH));
        for (int i = 0; i < speechNodes.getLength(); i++) {
            Element speechElement = (Element) speechNodes.item(i);
            Speech speech = DOMPlayParser.getSpeech(speechElement);
            speeches.add(speech);
        }
        return speeches;
    }
}
