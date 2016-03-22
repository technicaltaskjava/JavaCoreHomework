package com.epam.javase04.task01_bookHandler.comparator;

import com.epam.javase04.task01_bookHandler.wordsHandler.Word;

import java.util.Comparator;

public class WordsComparator implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return Double.compare(word1.getVowelsPercentage(), word2.getVowelsPercentage());
    }

}
