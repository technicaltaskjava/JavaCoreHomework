package com.epam.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Yuriy Krishtop on 09.04.2016.
 */
public class DOMParser {

    private DOMParser() {
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Document document;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        if (args.length == 0) {
            URL input = new URL("http://www.ibiblio.org/xml/examples/shakespeare/rich_ii.xml");
            document = db.parse(String.valueOf(input));
        } else if (args[0].contains("http://")) {
            URL input = new URL(args[0]);
            document = db.parse(String.valueOf(input));
        } else {
            File input = new File(args[0]);
            document = db.parse(String.valueOf(input));
        }
        db.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws IOException {
                if (systemId.contains("play.dtd")) {
                    return new InputSource("http://www.ibiblio.org/xml/examples/play.dtd");
                } else {
                    return null;
                }
            }
        });
        Element root = document.getDocumentElement();
        HashMap<String, Speaker> speakers = new HashMap();
        NodeList speechesNodes = root.getElementsByTagName("SPEECH");
        for (int i = 0; i < speechesNodes.getLength(); i++) {
            Element speechesElement = (Element) speechesNodes.item(i);
            Speaker speaker = getSpeaker(speechesElement);
            if (speakers.containsKey(speaker.getName())) {
                int oldCountSpeech = speakers.get(speaker.getName()).getCountSpeech();
                int oldCountWords = speakers.get(speaker.getName()).getCountWord();
                speakers.get(speaker.getName()).setCountSpeech(oldCountSpeech + 1);
                speakers.get(speaker.getName()).setCountWord(oldCountWords + speaker.getCountWord());
            } else {
                speakers.put(speaker.getName(), speaker);
            }
        }
        for (Speaker s : speakers.values()) {
            System.out.println(s);
        }
    }

    private static Speaker getSpeaker(Element speechesElement) {
        Speaker speaker = new Speaker();
        speaker.setName(getSingleChild(speechesElement, "SPEAKER").getTextContent().trim());
        speaker.setCountSpeech(1);
        NodeList linesNodes = speechesElement.getElementsByTagName("LINE");
        String text = "";
        for (int i = 0; i < linesNodes.getLength(); i++) {
            Element line = (Element) linesNodes.item(i);
            text += line.getTextContent().trim() + " ";
        }
        speaker.setCountWord(text.split("[ ]+").length);
        return speaker;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        return (Element) nodeList.item(0);
    }

}