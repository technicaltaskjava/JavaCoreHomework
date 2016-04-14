package com.epam.task1.otherclasses;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oleg on 09.04.2016.
 */
public class Speech {

    private String speaker;
    private List<String> speechList;

    public Speech() {
        this.speechList = new LinkedList<>();
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public List<String> getSpeech() {
        return speechList;
    }

    public void addLine(String text) {
        speechList.add(text);
    }

    public void setSpeech(List<String> speech) {
        this.speechList = speech;
    }

    public int getWordsInSpeech() {
        int words = 0;
        for (String string :
                speechList) {
            words += string.split("\\s+").length;
        }
        return words;
    }

}
