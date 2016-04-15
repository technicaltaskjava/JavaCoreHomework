package com.epam.task1.util;

import com.epam.task1.model.Speaker;
import com.epam.task1.model.Speech;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Kramska on 10-Apr-16.
 */
public class PlayAnalyser {
    private PlayAnalyser() {
    }

    public static Map<String, Speaker> analyse(List<Speech> speechList) {
        Map<String, Speaker> speakerMap = new HashMap<>();
        for (Speech speech : speechList) {
            String[] speechText = speech.getCue().split("\\s");
            if (!speakerMap.containsKey(speech.getSpeaker())) {
                speakerMap.put(speech.getSpeaker(), new Speaker(speech.getSpeaker(), 1, speechText.length));
            } else {
                Speaker speaker = speakerMap.get(speech.getSpeaker());
                int cues = speaker.getCueCount();
                int words = speaker.getWordsCount();
                speaker.setCueCount(cues + 1);
                speaker.setWordsCount(words + speechText.length);
                speakerMap.put(speech.getSpeaker(), speaker);
            }
        }
        return speakerMap;
    }
}
