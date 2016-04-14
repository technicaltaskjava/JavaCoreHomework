package com.epam.task1;

import com.epam.task1.dom.MyDOMParser;
import com.epam.task1.otherclasses.Pair;
import com.epam.task1.otherclasses.Speech;
import com.epam.task1.sax.MySAXParser;
import com.epam.task1.stax.MyStAXParser;

import java.util.*;

/**
 * Created by Oleg on 11.04.2016.
 */
public class Controller {

    private Controller() {
    }

    public static List<Speech> performParse(String parser, String url) {

        switch (parser) {
            case "DOM":
                return MyDOMParser.performParse(url);
            case "SAX":
                return MySAXParser.performParse(url);
            default:
                return MyStAXParser.performParse(url);
        }
    }

    public static Map<String, Pair> countStatistics(List<Speech> list) {
        Map<String, Pair> map = new HashMap<>();

        for (Speech speech :
                list) {
            int wordsInCurrentSpeech = speech.getWordsInSpeech();
            String speaker = speech.getSpeaker();
            if (map.containsKey(speaker)) {
                int words = wordsInCurrentSpeech + map.get(speaker).getWordsCount();
                int speeches = map.get(speaker).getSpeechCount() + 1;
                map.remove(speaker);
                map.put(speaker, new Pair(speeches, words));
            } else {
                map.put(speaker, new Pair(1, speech.getWordsInSpeech()));
            }
        }
        return map;
    }
}
