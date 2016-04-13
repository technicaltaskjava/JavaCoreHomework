package javase.t01.dom;

import javase.t01.exception.PlayParserException;
import javase.t01.play.Play;
import javase.t01.play.Speech;
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
import java.io.StringReader;

/**
 * Play Parser Class (method DOM)
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class PlayParserDom {
    private PlayParserDom() {}

    public static Play parsing(String xmlFileName) throws PlayParserException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            ignoreDTD(db);
            Document document = db.parse(new File(xmlFileName));
            Element root = document.getDocumentElement();
            NodeList speechNodes = root.getElementsByTagName("SPEECH");

            Play play = new Play();
            for (int i = 0; i < speechNodes.getLength(); i++) {
                Element speechElement = (Element) speechNodes.item(i);
                Speech speech = new Speech();
                for (String speaker : getMultiChild(speechElement, "SPEAKER")) {
                    speech.addSpeaker(speaker);
                }
                for (String line : getMultiChild(speechElement, "LINE")) {
                    speech.addLine(line);
                }
                play.add(speech);
            }
            return play;

        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new PlayParserException(e);
        }
    }

    private static String[] getMultiChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        String[] lines = new String[nodeList.getLength()];
        for (int index = 0; index < nodeList.getLength(); index++) {
            lines[index]  = nodeList.item(index).getTextContent().trim();
        }
        return lines;
    }

    private static void ignoreDTD(DocumentBuilder db) {
        db.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) {
                return new InputSource(new StringReader(""));
            }
        });
    }
}
