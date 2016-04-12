package com.epam.task1.sax;

import com.epam.task1.otherclasses.Speech;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class MySAXHandler extends DefaultHandler {

    private List<Speech> speechList = new LinkedList<>();
    private Speech speech;
    private StringBuilder text;

    public List<Speech> getSpeechList() {
        return speechList;
    }

    @Override
    public void startDocument() throws SAXException {
        //
    }

    @Override
    public void endDocument() throws SAXException {
        //
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if ("SPEECH".equals(qName)) {
            speech = new Speech();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        switch (qName.toUpperCase()) {
            case "SPEAKER":
                speech.setSpeaker(text.toString());
                break;
            case "LINE":
                speech.addLine(text.toString());
                break;
            case "SPEECH":
                speechList.add(speech);
                speech = null;
                break;
            default:
                break;
        }
    }


}
