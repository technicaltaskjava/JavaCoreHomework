package com.epam.task1;

/**
 * Created by Yuriy Krishtop on 09.04.2016.
 */
public class Speaker {
    private String name;
    private int countSpeech;
    private int countWord;

    public String getName() {
        return name;
    }

    public int getCountWord() {
        return countWord;
    }

    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountSpeech(int countSpeech) {
        this.countSpeech = countSpeech;
    }

    public int getCountSpeech() {
        return countSpeech;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", count of speech: " + countSpeech + ", count of words: " + countWord +
                ", average  count of words in speech: " + (double) countWord / countSpeech;
    }
}
