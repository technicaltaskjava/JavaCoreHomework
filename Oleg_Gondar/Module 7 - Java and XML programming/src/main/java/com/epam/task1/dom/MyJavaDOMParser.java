package com.epam.task1.dom;

import com.epam.task1.otherclasses.Speech;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by O.Gondar on 13.04.2016.
 */
public class MyJavaDOMParser {

    private static final Logger logger = Logger.getLogger(MySunDOMParser.class);

    private MyJavaDOMParser() {
    }

    public static List<Speech> performParse(String url) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("SPEECH");
            return parse(nList);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            org.apache.log4j.BasicConfigurator.configure();
            logger.error(e);
        }
        return Collections.emptyList();
    }

    private static List<Speech> parse(NodeList nodeList) {
        List<Speech> speechList = new LinkedList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ("SPEECH".equals(node.getNodeName())) {
                speechList.add(getSpeech(node.getChildNodes()));
            }
        }
        return speechList;
    }

    private static Speech getSpeech(NodeList nodeList) {
        Speech speech = new Speech();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ("SPEAKER".equals(node.getNodeName())) {
                speech.setSpeaker(node.getTextContent().trim());
            } else if ("LINE".equals(node.getNodeName())) {
                speech.addLine(node.getTextContent().trim());
            }
        }
        return speech;
    }

}





