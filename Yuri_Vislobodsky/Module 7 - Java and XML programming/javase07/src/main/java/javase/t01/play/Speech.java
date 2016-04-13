package javase.t01.play;

import java.util.ArrayList;
import java.util.List;

/**
 * Speech class for Play
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public class Speech {
    private List<String> speakers;
    private int wordsCount;

    public Speech() {
        speakers = new ArrayList<>();
        wordsCount = 0;
    }

    public void addSpeaker(String speaker) {
        speakers.add(speaker);
    }

    public void addLine(String line) {
        this.wordsCount += line.split("[^A-Za-z]+").length;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public int getSpeakersCount() {
        return speakers.size();
    }

    public String getSpeaker(int index) {
        return (index >= 0 && index < getSpeakersCount()) ? speakers.get(index) : "";
    }
}
