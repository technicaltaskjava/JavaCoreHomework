package com.epam.javase04.task01_bookHandler.wordsHandler;

import com.epam.javase04.task01_bookHandler.comparator.WordsComparator;
import com.epam.javase04.task01_bookHandler.splitter.TextSplitter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsHandler {
    TextSplitter splitter = new TextSplitter();
    private Pattern pattern = Pattern.compile("[бвгджзйклмнпрстфхцчшщbcdfghjklmnpqrstvwxz]");

    public String[] swapWords(String inputFile) {
        String[] allSentences = splitter.splitToSentences(inputFile);

        for(int i = 0; i < allSentences.length; i++) {
            String[] sentenceWords = allSentences[i].split(" ");
            String tmpWord = sentenceWords[0];
            sentenceWords[0] = sentenceWords[sentenceWords.length - 1];
            sentenceWords[sentenceWords.length - 1] = tmpWord;

            String newSentence = "";
            for(int j = 0; j < sentenceWords.length; j++) {
                newSentence = newSentence + sentenceWords[j] + " ";
            }
            allSentences[i] = newSentence;
        }
        System.out.println("First and second words have reversed in every sentence.");
        return allSentences;
    }

    public Word[] sortWordsByVowelsPercentage(String inputFile) {
        String[] wordsFromText = splitter.splitToWords(inputFile);
        Word[] words = new Word[wordsFromText.length];

        for(int i = 0; i < words.length; i++) {
            words[i] = new Word(wordsFromText[i]);
            words[i].countVowelsPercentage();
        }
        Arrays.sort(words, new WordsComparator());
        System.out.println("Words have sorted by vowels percentage.");
        return words;
    }

    public StringBuilder deleteSpecificWord(int wordLength, String inputFile) {
        String[] wordsFromText = splitter.splitToWords(inputFile);
        StringBuilder deletedWords = new StringBuilder();
        StringBuilder otherWords = new StringBuilder();

        int index;
        int countMatchedWords = 0;
        for (index = 0; index < wordsFromText.length; index++) {
            if (wordsFromText[index].length() == wordLength) {
                Matcher matcher = pattern.matcher(wordsFromText[index].toLowerCase());
                if (matcher.lookingAt()) {
                    countMatchedWords++;
                    deletedWords.append(wordsFromText[index] + "\r\n");
                }
            } else {
                otherWords.append(wordsFromText[index] + "\r\n");
            }
        }
        System.out.print("Have found and deleted " + countMatchedWords +
                " word(s) with length " + wordLength + " letter(s) beginning with a consonant. ");
        System.out.println("Other words have written. ");
        if (countMatchedWords == 0) {
            System.out.println("No words of this length in the text.");
        }
        return otherWords;
    }

    public StringBuilder changeWord(String inputFile) {
        String[] wordsFromText = splitter.splitToWords(inputFile);
        StringBuilder changedWords = new StringBuilder("All occurrence of the first letter in each word was delete:\r\n");

        for(int i = 0; i < wordsFromText.length; i++) {
            char firstChar = wordsFromText[i].toLowerCase().charAt(0);
            String changedWord = "";

            for (int j = 0; j < wordsFromText[i].length(); j++) {
                if (wordsFromText[i].toLowerCase().charAt(j) != firstChar) {
                    changedWord += wordsFromText[i].charAt(j);
                }
            }
            changedWords.append(firstChar + changedWord + "\r\n");
        }
        System.out.print("All occurrence of the first letter in each word has deleted. ");
        System.out.println("Changed words have written. ");
        return changedWords;
    }
}
