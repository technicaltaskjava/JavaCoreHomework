package xml.dom;

import helper.XMLHelpClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.XMLTags;
import xml.actor.Actor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Ushakov
 */
public class DOMActorParser {
    private DOMActorParser() {
    }

    public static Set<Actor> parseActors(File xmlFile) throws SAXException {
        try {

            Map<String, Actor> actors = new HashMap<>();


            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();
            NodeList speakerList = document.getElementsByTagName(XMLTags.SPEECH.toString());

            for (int i = 0; i < speakerList.getLength(); i++) {
                Node node = speakerList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    parseSpeechTag(actors, node);
                }
            }

            return new HashSet<>(actors.values());
        } catch (ParserConfigurationException | IOException e) {
            throw new SAXException(e.getMessage(), e);
        }

    }

    private static void parseSpeechTag(Map<String, Actor> actors, Node node) {
        Element element = (Element) node;

        String actorName = getElementContent(element, XMLTags.SPEAKER.toString());
        Actor actor = XMLHelpClass.getActor(actors, actorName);
        actor.incReplicasCount();

        NodeList lines = element.getElementsByTagName(XMLTags.LINE.toString());

        for (int lineNumber = 0; lineNumber < lines.getLength(); lineNumber++) {
            Node lineNode = lines.item(lineNumber);

            if (lineNode.getNodeType() == Node.ELEMENT_NODE) {
                String line = lineNode.getTextContent();
                actor.addWordsCount(XMLHelpClass.getWordsCount(line));
            }
        }
    }

    private static String getElementContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
