package com.epam.task1;

import com.epam.task1.dom.MyJavaDOMParser;
import com.epam.task1.dom.MySunDOMParser;
import com.epam.task1.otherclasses.Pair;
import com.epam.task1.otherclasses.Speech;
import com.epam.task1.sax.MySAXParser;
import com.epam.task1.stax.MyStAXParser;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by Oleg on 11.04.2016.
 */
public class Controller {

    private static final Logger logger = Logger.getLogger(View.class);

    private Controller() {
    }

    public static List<Speech> performParse(String parser, String url) {

        switch (parser) {
            case "JavaDOM":
                return MyJavaDOMParser.performParse(url);
            case "SunDOM":
                return MySunDOMParser.performParse(url);
            case "SAX":
                return MySAXParser.performParse(url);
            default:
                try {
                    return MyStAXParser.performParse(url);
                } catch (IOException e) {
                    logger.error(e);
                }
        }
        return Collections.emptyList();
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
