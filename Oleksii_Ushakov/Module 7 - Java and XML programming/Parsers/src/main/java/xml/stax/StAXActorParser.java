package xml.stax;

import helper.XMLHelpClass;
import xml.XMLTags;
import xml.actor.Actor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Ushakov
 */
public class StAXActorParser {
    private static String checkedTag = "";
    private static Map<String, Actor> actors = new HashMap<>();
    private static Actor currentActor;

    private StAXActorParser() {
    }

    public static Set<Actor> parseActors(File xmlFile) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(xmlFile));

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        checkedTag = reader.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        parseCharacters(reader.getText());
                        break;
                    default:
                        checkedTag = "";
                }
            }

            return new HashSet<>(actors.values());
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new XMLStreamException(e.getMessage(), e);
        }

    }

    private static void parseCharacters(String text) {
        if (checkedTag.equals(XMLTags.SPEAKER.toString())) {
            currentActor = XMLHelpClass.getActor(actors, text);
            currentActor.incReplicasCount();
        } else if ((checkedTag.equals(XMLTags.LINE.toString())) && (currentActor != null)) {
            currentActor.addWordsCount(XMLHelpClass.getWordsCount(text));
        }
    }

}
