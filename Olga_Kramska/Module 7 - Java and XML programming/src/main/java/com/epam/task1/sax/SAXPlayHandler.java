package com.epam.task1.sax;

import com.epam.task1.type.TagName;
import com.epam.task1.model.Speech;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 10-Apr-16.
 */
public class SAXPlayHandler extends DefaultHandler {
    private List<Speech> speechList = new ArrayList<>();
    private Speech speech;
    private StringBuilder speechText;
    private StringBuilder text;

    public List<Speech> getSpeechList() {
        return speechList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (String.valueOf(TagName.SPEECH).equals(qName)) {
            speech = new Speech();
            speechText = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (TagName.SPEAKER.name().equals(qName)) {
            speech.setSpeaker(text.toString());
        } else if (TagName.LINE.name().equals(qName)) {
            speechText.append(text).append('\n');
        } else if (TagName.SPEECH.name().equals(qName)) {
            speech.setCue(speechText.toString());
            speechList.add(speech);
        }
    }
}
