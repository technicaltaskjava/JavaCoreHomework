package com.epam.task1;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Kramska on 18-Mar-16.
 */
public class Sentence {
    private static final String PUNCTUATIONS = "[,;:\u2012-\u2015«»\"\']";
    private String[] words;

    public Sentence(String string) {
        Pattern pattern = Pattern.compile(PUNCTUATIONS);
        Matcher matcher = pattern.matcher(string);
        String sent = matcher.replaceAll(" ");
        String sentence = sent.trim();
        words = sentence.split(" +");
    }

    public String[] getWords() {
        return words;
    }

    public void replaceFirstWord2Last() {
        String[] newSentence = Arrays.copyOf(words, words.length);
        if (words.length > 1) {
            newSentence[0] = words[words.length - 1];
            newSentence[newSentence.length - 1] = words[0];
        }
        words = newSentence;
    }

    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        for (String word : words) {
            sentence.append(word).append(" ");
        }
        return sentence.toString().trim();
    }
}