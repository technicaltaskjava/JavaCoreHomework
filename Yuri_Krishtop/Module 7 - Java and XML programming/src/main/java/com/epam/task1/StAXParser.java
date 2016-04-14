package com.epam.task1;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Yuriy Krishtop on 10.04.2016.
 */
public class StAXParser {
    private static int countWords = 0;
    private static HashMap<String, Speaker> speakers;

    private StAXParser() {
    }

    public static void main(String[] args) throws IOException, XMLStreamException {
        InputStream input = getSource(args);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(input);
        speakers = new HashMap();
        Speaker speaker = null;
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if ("SPEAKER".equals(startElement.getName().getLocalPart())) {
                    speaker = new Speaker();
                    speaker.setName(xmlEventReader.getElementText());
                    speaker.setCountSpeech(1);
                } else if ((speaker != null) && "LINE".equals(startElement.getName().getLocalPart())) {
                    xmlEvent = (XMLEvent) xmlEventReader.next();
                    speaker = wordCounterForSpeaker(xmlEvent, speaker);
                }
            } else if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if ((speaker != null) && "SPEECH".equals(endElement.getName().getLocalPart())) {
                    speaker = addCountWordCountSpeech(speaker);
                }
            }
        }
        showInfo();
    }

    private static InputStream getSource(String[] args) throws IOException {
        InputStream input;
        if (args.length == 0) {
            URL url = new URL("http://www.ibiblio.org/xml/examples/shakespeare/rich_ii.xml");
            input = url.openStream();
        } else if (args[0].contains("http://")) {
            URL url = new URL(args[0]);
            input = url.openStream();
        } else {
            input = new FileInputStream(args[0]);
        }
        return input;
    }

    private static void showInfo() {
        for (Speaker s : speakers.values()) {
            System.out.println(s);
        }
    }

    private static Speaker wordCounterForSpeaker(XMLEvent xmlEvent, Speaker speaker) {
        if (xmlEvent.isCharacters()) {
            String line = xmlEvent.asCharacters().getData().trim();
            String[] words = line.split(" ");
            countWords += words.length;
            speaker.setCountWord(countWords);
        }
        return speaker;
    }

    private static Speaker addCountWordCountSpeech(Speaker speaker) {
        if (speakers.containsKey(speaker.getName())) {
            int oldCountSpeech = speakers.get(speaker.getName()).getCountSpeech();
            int oldCountWords = speakers.get(speaker.getName()).getCountWord();
            speakers.get(speaker.getName()).setCountSpeech(oldCountSpeech + 1);
            speakers.get(speaker.getName()).setCountWord(oldCountWords + countWords);
            countWords = 0;
            return speaker;
        } else {
            speakers.put(speaker.getName(), speaker);
            countWords = 0;
            return null;
        }
    }

}
