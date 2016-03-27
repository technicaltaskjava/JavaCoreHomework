package com.epam.javase04.task01_bookHandler.splitter;

import com.epam.javase04.task01_bookHandler.reader.dataReader;

public class TextSplitter {
    com.epam.javase04.task01_bookHandler.reader.dataReader dataReader = new dataReader();
    StringBuilder text;

    public String[] splitToSentences(String file) {
        text = dataReader.readFile(file);
        String pattern = "[.!?]\\s*";
        String allText = text.toString();
        String[] allSentences = allText.split(pattern);
        return allSentences;
    }

    public String[] splitToWords(String file) {
        text = dataReader.readFile(file);
        String pattern = "[\\p{Punct}\\s]+";
        String allText = text.toString();
        String[] allWords = allText.split(pattern);
        return allWords;
    }
}
