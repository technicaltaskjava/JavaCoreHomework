package com.kokhanyuk.xml.parsers;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DomParser
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class DomParser {
    static Logger log = Logger.getLogger(DomParser.class);

    public void domParsing() {

        Map<String, Speaker> speaker = new HashMap();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("hamlet.xml"));
            Element root = document.getDocumentElement();
            NodeList speechNodes = root.getElementsByTagName("SPEECH");
            for (int i = 0; i < speechNodes.getLength(); i++) {
                Element speechElement = (Element) speechNodes.item(i);
                String name = getSingleChild(speechElement, "SPEAKER").getTextContent().trim();
                if (!speaker.containsKey(name)) {
                    speaker.put(name, new Speaker());
                }
                String[] word = getAllStringChild(speechElement, "LINE").split(" ");
                speaker.get(name).setCounterReplica();
                speaker.get(name).setCounterWord(word.length);
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.warn(e.getMessage(), e);
        }
        for (Map.Entry<String, Speaker> speak : speaker.entrySet()) {
            String message = speak.getKey() + ": replica: " + speak.getValue().getCounterReplica() + " average number of words: "
                    + speak.getValue().getAverageWords();
            log.info(message);
        }
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        return (Element) nlist.item(0);
    }

    private static String getAllStringChild(Element element, String childName) {
        StringBuilder sb = new StringBuilder();
        NodeList nlist = element.getElementsByTagName(childName);
        for (int i = 0; i < nlist.getLength(); i++) {
            Element child = (Element) nlist.item(i);
            sb.append(child.getTextContent());
        }
        return sb.toString();
    }

}
