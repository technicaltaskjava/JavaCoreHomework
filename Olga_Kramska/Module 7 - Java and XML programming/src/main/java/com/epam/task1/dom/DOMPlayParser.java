package com.epam.task1.dom;

import com.epam.task1.model.Speech;
import org.w3c.dom.DOMException;
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
    private DOMPlayParser() {
    }

    private static Element getDocumentElement(InputStream inputStream) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, e.getMessage());
        }
        Document document;
        try {
            document = db.parse(inputStream);
        } catch (SAXException | IOException e) {
            throw new DOMException(DOMException.SYNTAX_ERR, e.getMessage());
        }
        return document.getDocumentElement();
    }

    public static Speech getSpeech(Element speechElement) {
        Speech speech = new Speech();
        speech.setSpeaker(getSpeakerName(speechElement));
        speech.setCue(getCueText(speechElement));
        return speech;
    }

    private static String getSpeakerName(Element element) {
        NodeList nodeList = element.getElementsByTagName("SPEAKER");
        Element child = (Element) nodeList.item(0);
        return child.getTextContent().trim();
    }

    private static String getCueText(Element element) {
        NodeList nodeList = element.getElementsByTagName("LINE");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element line = (Element) nodeList.item(i);
            sb.append(line.getTextContent().trim());
            sb.append('\n');
        }
        return sb.toString();
    }

    public static List<Speech> getSpeechesFromXML(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        Element play = getDocumentElement(inputStream);
        List<Speech> speeches = new ArrayList<>();
        NodeList speechNodes = play.getElementsByTagName("SPEECH");
        for (int i = 0; i < speechNodes.getLength(); i++) {
            Element speechElement = (Element) speechNodes.item(i);
            Speech speech = DOMPlayParser.getSpeech(speechElement);
            speeches.add(speech);
        }
        return speeches;
    }
}
