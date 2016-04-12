package com.epam.first.dom;

import com.epam.first.Operation;
import com.epam.first.PlayList;
import com.epam.first.Speaker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ParsingByDom {
    private static Logger classLog = Logger.getLogger("DOM_log");

    private ParsingByDom() {
    }

    public static PlayList parseRun(String play) {
        PlayList speakerList = new PlayList();
        try {
            DocumentBuilderFactory currentDBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = currentDBFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new File(play));

            Element root = document.getDocumentElement();

            NodeList speechNodes = root.getElementsByTagName("SPEECH");

            for (int speechCounter = 0; speechCounter < speechNodes.getLength(); speechCounter++) {
                Element speechElement = (Element) speechNodes.item(speechCounter);
                Speaker speaker = getSpeakerData(speechElement);
                speakerList.addSpeaker(speaker);
            }
        }
        catch (SAXException | IOException | ParserConfigurationException parsingException) {
            classLog.info(String.valueOf(parsingException));
        }
        return speakerList;
    }

    private static Speaker getSpeakerData(Element speechElement) {
        Speaker currentSpeaker = new Speaker();
        currentSpeaker.setName(getElementValue(speechElement, "SPEAKER").trim());
        currentSpeaker.setNumberOfSpeech(1);
        currentSpeaker.setAverageSpeechWords(Operation.countSpeechWords(getElementValue(speechElement, "LINE").trim()));
        return currentSpeaker;
    }

    private static String getElementValue(Element element, String childName) {
        NodeList nodelist = element.getElementsByTagName(childName);
        String speechText = "";
        for (int speechCounter = 0; speechCounter < nodelist.getLength(); speechCounter++) {
            speechText = speechText + nodelist.item(speechCounter).getTextContent() + " ";
        }
        return speechText;
    }



}
