package com.epam.first.sax;

import com.epam.first.Operation;
import com.epam.first.ParseTagNames;
import com.epam.first.PlayList;
import com.epam.first.Speaker;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PlaySaxHandler extends DefaultHandler {
    private PlayList speakerList = new PlayList();
    private Speaker speaker;
    private StringBuilder text;
    private String speechText = "";

    public PlayList getSpeakerList() {
        return speakerList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if ("SPEECH".equals(qName)) {
            speaker = new Speaker();
            speechText = "";
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        ParseTagNames tagName = ParseTagNames.valueOf(qName.toUpperCase());
        switch (tagName) {
            case SPEAKER:
                speaker.setName(text.toString());
                break;
            case LINE:
                speechText = speechText + text.toString() + " ";
                break;
            case SPEECH:
                speaker.setNumberOfSpeech(1);
                speaker.setAverageSpeechWords(Operation.countSpeechWords(speechText));
                speakerList.addSpeaker(speaker);
                speaker = null;
                break;
            default:
                break;
        }
    }
}
