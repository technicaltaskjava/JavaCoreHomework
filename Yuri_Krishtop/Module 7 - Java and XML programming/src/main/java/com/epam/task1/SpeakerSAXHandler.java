package com.epam.task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuriy Krishtop on 10.04.2016.
 */
public class SpeakerSAXHandler extends DefaultHandler {

    private HashMap<String, Speaker> speakerHashMap = new HashMap();
    private Speaker speaker;
    private StringBuilder text;
    private int wordCount = 0;

    public Map<String, Speaker> getSpeakerHashMap() {
        return speakerHashMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if ("SPEECH".equals(qName)) {
            speaker = new Speaker();
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("SPEAKER".equals(qName)) {
            speaker.setName(text.toString());
        } else if ("LINE".equals(qName)) {
            speaker.setCountSpeech(1);
            String[] words = text.toString().split("[ ]+");
            wordCount += words.length;
            speaker.setCountWord(wordCount);
        } else if ("SPEECH".equals(qName) && (speaker != null)) {
            if (speakerHashMap.containsKey(speaker.getName())) {
                int oldCountSpeech = speakerHashMap.get(speaker.getName()).getCountSpeech();
                int oldCountWords = speakerHashMap.get(speaker.getName()).getCountWord();
                speakerHashMap.get(speaker.getName()).setCountSpeech(oldCountSpeech + 1);
                speakerHashMap.get(speaker.getName()).setCountWord(oldCountWords + wordCount);
            } else {
                speakerHashMap.put(speaker.getName(), speaker);
                speaker = null;
            }
            wordCount = 0;
        }
    }

}
