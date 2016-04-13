package xml.sax;

import org.xml.sax.SAXException;
import xml.actor.Actor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Alexey Ushakov
 */
public class SAXActorParser {
    private SAXActorParser() {
    }

    public static Set<Actor> parseActors(File xmlFile) throws SAXException {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SAXActorHandler actorHandler = new SAXActorHandler();

            parser.parse(xmlFile, actorHandler);

            return actorHandler.getActors();

        } catch (ParserConfigurationException | IOException e) {
            throw new SAXException(e.getMessage(), e);
        }

    }
}
