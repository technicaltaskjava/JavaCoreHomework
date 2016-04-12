package com.kokhanyuk.xml.parsers;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 *SaxHandler
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SaxHandler extends DefaultHandler {
    static Logger log = Logger.getLogger(SaxParser.class);
    String text;
    String name;
    String speech = "SPEECH";
    private Map<String, Speaker> speaker = new HashMap();
    boolean insideSpeech;

    public Map<String, Speaker> getRezult() {
        return speaker;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals(speech)) {
            insideSpeech = true;
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text = new String(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (insideSpeech) {
            TagName tagName = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
            switch (tagName) {
                case SPEECH:
                    speaker.get(name).setCounterReplica();
                    insideSpeech = false;
                    break;
                case SPEAKER:
                    if (!speaker.containsKey(text)) {
                        speaker.put(text, new Speaker());
                    }
                    name = text;
                    break;
                case LINE:
                    String[] word = text.split(" ");
                    speaker.get(name).setCounterWord(word.length);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void warning(SAXParseException exception) {
        log.warn(exception.getMessage(), exception);
    }

    @Override
    public void error(SAXParseException exception) {
        log.warn(exception.getMessage(), exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        log.warn(exception.getMessage(), exception);
        throw exception;
    }
}
