package com.epam.task1.util;

import com.epam.task1.BookReader;
import com.epam.task1.Word;

import java.util.Arrays;

/**
 * Created by Olga Kramska on 19-Mar-16.
 */
public class BookUtils {
    private BookUtils(){}

    public static Word[] getWordsArray(String text){
        String[] wordsAsString = BookReader.getWordsFromText(text);
        Word[] words = new Word[wordsAsString.length];
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word(wordsAsString[i]);
        }
        return words;
    }

    public static String delWordsFromText(String text, int defSize) {
        Word[] words = getWordsArray(text);
        StringBuilder result = new StringBuilder();
        for (Word word : words) {
            if (word.isStartWithVowel() || word.getWordLength() != defSize) {
                result.append(word);
                result.append(" ");
            } else {
                result.append("");
            }
        }
        return result.toString();
    }

    public static Word[] sortWords(String text){
        Word[] words = getWordsArray(text);
        Arrays.sort(words);
        return words;
    }
}
