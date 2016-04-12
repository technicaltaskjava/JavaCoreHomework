package com.epam.task1.otherclasses;

/**
 * Created by O.Gondar on 11.04.2016.
 */
public class Pair {

    private int speechCount;
    private int wordsCount;

    public Pair(int speechCount, int wordsCount) {
        this.speechCount = speechCount;
        this.wordsCount = wordsCount;
    }

    public int getSpeechCount() {
        return speechCount;
    }

    public void setSpeechCount(int speechCount) {
        this.speechCount = speechCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }
}
