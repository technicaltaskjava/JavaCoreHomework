package com.epam.task1.sax;

import com.epam.task1.model.Speech;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Olga Kramska on 12-Apr-16.
 */
public class SAXPlayParser {
    private SAXPlayParser() {
    }

    public static List<Speech> getSpeechesFromXML(InputStream inputStream) throws SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new SAXException(e.getMessage());
        }
        SAXPlayHandler handler = new SAXPlayHandler();
        try {
            parser.parse(new InputSource(inputStream), handler);
        } catch (SAXException | IOException e) {
            throw new SAXException(e.getMessage());
        }
        return handler.getSpeechList();
    }
}
