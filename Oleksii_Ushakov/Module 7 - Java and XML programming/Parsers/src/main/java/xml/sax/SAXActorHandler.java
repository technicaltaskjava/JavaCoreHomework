package xml.sax;

import helper.XMLHelpClass;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.XMLTags;
import xml.actor.Actor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Ushakov
 */
public class SAXActorHandler extends DefaultHandler {
    private Map<String, Actor> actors;
    private String checkedTag;
    private Actor currentActor;


    public SAXActorHandler() {
        super();
        this.actors = new HashMap<>();
    }

    public Set<Actor> getActors() {
        return new HashSet<>(actors.values());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        checkedTag = qName;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (checkedTag.equals(XMLTags.SPEAKER.toString())) {
            String actorName = new String(ch, start, length);
            currentActor = XMLHelpClass.getActor(actors, actorName);
            currentActor.incReplicasCount();
        } else if (checkedTag.equals(XMLTags.LINE.toString())) {
            currentActor.addWordsCount(XMLHelpClass.getWordsCount(new String(ch, start, length)));
        }
    }
}
