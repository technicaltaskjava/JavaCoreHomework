package helper;

import xml.actor.Actor;

import java.util.Map;

/**
 * @author Alexey Ushakov
 */
public class XMLHelpClass {
    private XMLHelpClass() {
    }

    public static int getWordsCount(String line) {
        String text = line.replaceAll("[?!,.]", "");
        String[] words = text.split("\\s");
        return words.length;
    }

    public static Actor getActor(Map<String, Actor> actors, String actorName) {
        if (!actors.containsKey(actorName)) {
            actors.put(actorName, new Actor(actorName));
        }
        return actors.get(actorName);
    }
}
