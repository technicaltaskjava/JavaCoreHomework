package com.epam.first.stax;

import com.epam.first.Operation;
import com.epam.first.ParseTagNames;
import com.epam.first.PlayList;
import com.epam.first.Speaker;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class ParsingByStax {
    private static Logger classLog = Logger.getLogger("STAX_log");
    private static PlayList speakerList = new PlayList();
    private static Speaker speaker = new Speaker();
    private static ParseTagNames elementName = null;
    private static String speechText = "";

    private ParsingByStax() {
    }

    public static PlayList parseRun(String play) {
        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream inputStream = new FileInputStream(play);

            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            PlayList speakerList;
            speakerList = makeParse(reader);
            return speakerList;
        }
        catch (IOException | XMLStreamException parsingException) {
            classLog.info(String.valueOf(parsingException));
        }
        return null;
    }

    private static PlayList makeParse(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int elementType = reader.next();
            switch (elementType) {
                case XMLStreamConstants.START_ELEMENT:
                    proceedStartElement(reader);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    proceedCharacters(reader);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    proceedEndElement(reader);
                    break;
                default:
                    break;
            }
        }
        return speakerList;
    }

    private static void proceedStartElement(XMLStreamReader reader) {
        elementName = ParseTagNames.valueOf(reader.getLocalName().toUpperCase());
        if ("SPEECH".equals(elementName.name())) {
            speechText = "";
            speaker = new Speaker();
        }
    }

    private static void proceedEndElement(XMLStreamReader reader) {
        elementName = ParseTagNames.valueOf(reader.getLocalName().toUpperCase());
        if ("SPEECH".equals(elementName.name())) {
            speaker.setNumberOfSpeech(1);
            speaker.setAverageSpeechWords(Operation.countSpeechWords(speechText));
            speakerList.addSpeaker(speaker);
            speaker = null;
        }
    }

    private static void proceedCharacters(XMLStreamReader reader) {
        String text = reader.getText().trim();
        if (!text.isEmpty()) {
            switch (elementName) {
                case SPEAKER:
                        speaker.setName(reader.getText().trim());
                    break;
                case LINE:
                    speechText = speechText + reader.getText().trim() + " ";
                    break;
                default:
                    break;
            }
        }
    }
}
