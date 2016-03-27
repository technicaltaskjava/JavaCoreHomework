package com.epam.task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Kramska on 19-Mar-16.
 */
public class Word implements Comparable<Word> {
    private final static Pattern VOWEL = Pattern.compile("[àå¸èîóûýþÿ]");

    private String word;

    public Word(String word) {
        this.word = word.toLowerCase();
    }

    public boolean isStartWithVowel() {
        Matcher matcher = VOWEL.matcher(word);
        return matcher.lookingAt();
    }

    public int getWordLength() {
        return word.length();
    }

    public String changeWord() {
        char[] letters = word.toCharArray();
        StringBuilder changedWord = new StringBuilder();
        changedWord.append(letters[0]);
        for (int i = 1; i < letters.length; i++) {
            if (letters[i] != letters[0]) {
                changedWord.append(letters[i]);
            }
        }
        return changedWord.toString();
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Word wordToCompare) {
        if (this.getVowelPart() > wordToCompare.getVowelPart()) {
            return 1;
        } else if (this.getVowelPart() == wordToCompare.getVowelPart()) {
            return 0;
        } else {
            return -1;
        }
    }

    private double getVowelPart() {
        double numOfVowel = 0;
        Matcher matcher = VOWEL.matcher(word);
        while (matcher.find()) {
            numOfVowel++;
        }
        return numOfVowel / word.length();
    }
}

