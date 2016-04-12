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
    private Map<String, Speaker> speakerMap;

    public PlayAnalyser(List<Speech> speechList) {
        speakerMap = new HashMap<>();
        for (Speech speech : speechList) {
            String[] speechText = speech.getCue().split("\\s");
            if (!speakerMap.containsKey(speech.getSpeaker())) {
                speakerMap.put(speech.getSpeaker(), new Speaker(1, speechText.length));
            } else {
                Speaker speaker = speakerMap.get(speech.getSpeaker());
                int cues = speaker.getCueCount();
                int words = speaker.getWordsCount();
                speaker.setCueCount(cues + 1);
                speaker.setWordsCount(words + speechText.length);
                speakerMap.put(speech.getSpeaker(), speaker);
            }
        }
    }

    public Map<String, Speaker> getSpeakerMap() {
        return speakerMap;
    }

    public void printPlayStatistic() {
        for (Map.Entry entry : speakerMap.entrySet()) {
            Speaker speaker = (Speaker) entry.getValue();
            System.out.println(entry.getKey() + ": cue count is " + speaker.getCueCount() +
                    ", average cue length is " + (speaker.getWordsCount() / speaker.getCueCount()));

        }
    }
}
