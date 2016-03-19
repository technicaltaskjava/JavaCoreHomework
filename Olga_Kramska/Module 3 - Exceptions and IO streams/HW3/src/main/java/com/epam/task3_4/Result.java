package com.epam.task3_4;

/**
 * Created by Olga Kramska on 13-Mar-16.
 */
public class Result {
    private String keyWord;
    private int occurrence;

    public Result(String keyWord, int occurrence) {
        this.keyWord = keyWord;
        this.occurrence = occurrence;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return keyWord + " - " + occurrence + "\n";
    }
}
