package com.epam.task1.stax;

import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class MyStAXParser {

    private static final String IGNORE_EXTERNAL_DTD_URL = "http://java.sun.com/xml/stream/properties/ignore-external-dtd";
    private static final String SPEECH_TAG_ID = "SPEECH";

    private static final Logger logger = Logger.getLogger(MyStAXParser.class);

    private MyStAXParser() {
    }

    public static List<Speech> performParse(String url) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty(IGNORE_EXTERNAL_DTD_URL, Boolean.TRUE);
        try {
            InputStream input = new URL(url).openStream();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            return process(reader);
        } catch (IOException | XMLStreamException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return Collections.emptyList();
    }

    private static List<Speech> process(XMLStreamReader reader) throws XMLStreamException {
        List<Speech> menu = new LinkedList<>();
        Speech speech = null;
        String elementName = null;
        while (reader.hasNext()) {

            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = reader.getLocalName();
                    if (SPEECH_TAG_ID.equals(elementName)) {
                        speech = new Speech();
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    processCharacters(elementName, speech, text);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = reader.getLocalName();
                    if (SPEECH_TAG_ID.equals(elementName)) {
                        menu.add(speech);
                    }
                    break;
                default:
                    break;
            }
        }
        return menu;
    }

    private static void processCharacters(String elementName, Speech speech, String text) {
        if (!text.isEmpty() && elementName != null && speech != null) {

            switch (elementName) {
                case "SPEAKER":
                    speech.setSpeaker(text);
                    break;
                case "LINE":
                    speech.addLine(text);
                    break;
                case "SPEECH":
                    break;
                default:
                    break;
            }
        }
    }
}
