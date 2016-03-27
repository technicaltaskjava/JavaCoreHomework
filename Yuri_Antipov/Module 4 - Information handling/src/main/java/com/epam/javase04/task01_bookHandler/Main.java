package com.epam.javase04.task01_bookHandler;

import com.epam.javase04.task01_bookHandler.wordsHandler.Word;
import com.epam.javase04.task01_bookHandler.wordsHandler.WordsHandler;
import com.epam.javase04.task01_bookHandler.writer.dataWriter;

public class Main {
    public static void main(String[] args) {
        String sampleOfText = "SampleText.txt";

        dataWriter writer = new dataWriter();
        WordsHandler wordsHandler = new WordsHandler();

        String[] sentencesAfterSwap = wordsHandler.swapWords(sampleOfText);
        writer.writeToFile(sentencesAfterSwap, "OutputSentencesReverse.txt");

        Word[] allWords = wordsHandler.sortWordsByVowelsPercentage(sampleOfText);
        writer.writeToFile(allWords, "OutputSortedWords.txt");

        StringBuilder otherWords = wordsHandler.deleteSpecificWord(1, sampleOfText);
        writer.writeToFile(otherWords, "OutputAfterDeletingSpecificWord.txt");

        StringBuilder changedWords = wordsHandler.changeWord(sampleOfText);
        writer.writeToFile(changedWords, "OutputChangedWords.txt");
    }
}
