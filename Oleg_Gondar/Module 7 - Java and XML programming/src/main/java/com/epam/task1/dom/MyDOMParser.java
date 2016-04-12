package com.epam.task1.dom;

import com.epam.task1.otherclasses.Speech;


import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class MyDOMParser {

    private static final String LOAD_EXTERNAL_DTD_URL = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    private static final Logger logger = Logger.getLogger(MyDOMParser.class);

    private MyDOMParser() {
    }

    public static List<Speech> performParse(String url) {

        DOMParser parser = new DOMParser();
        try {
            parser.setFeature(LOAD_EXTERNAL_DTD_URL, false);
            parser.parse(url);
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            List<Speech> menu = new LinkedList<>();

            NodeList speechNodes = root.getElementsByTagName("SPEECH");

            for (int i = 0; i < speechNodes.getLength(); i++) {
                Speech speech = new Speech();
                Element speechElement = (Element) speechNodes.item(i);
                speech.setSpeaker(getSingleChild(speechElement, "SPEAKER").getTextContent().trim());
                NodeList lineNodes = speechElement.getChildNodes();
                checkLINE(speech, lineNodes);
                menu.add(speech);
            }

            return menu;
        } catch (SAXException | IOException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return Collections.emptyList();
    }

    private static void checkLINE(Speech speech, NodeList lineNodes) {
        for (int j = 0; j < lineNodes.getLength(); j++) {
            if ("LINE".equals(lineNodes.item(j).getNodeName())) {
                speech.addLine(lineNodes.item(j).getTextContent().trim());
            }
        }
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        return (Element) nlist.item(0);
    }
}

