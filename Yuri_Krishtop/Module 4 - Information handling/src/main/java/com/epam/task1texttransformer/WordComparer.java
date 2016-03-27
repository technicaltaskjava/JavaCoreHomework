package com.epam.task1texttransformer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yuriy Krishtop on 19.03.2016.
 */

public class WordComparer implements Comparable<WordComparer> {
    private String word;

    public  WordComparer(String word) {
        this.word = word;
    }

    public String toString() {
        return word;
    }

    public String  getWord() {
        return word;
    }


    @Override
    public int compareTo( WordComparer other) {
        if (other.getVowelsFraction() > this.getVowelsFraction()) {
            return -1;
        } else if (other.getVowelsFraction() < this.getVowelsFraction()) {
            return 1;
        } else {
            return 0;
        }
    }

    public double getVowelsFraction() {
        if ((word != null) && (word != "")) {
            Pattern p = Pattern.compile("[aeioyuауоыиэяюёеАУОЫИЭЯЮЁЕ]", Pattern.CASE_INSENSITIVE);
            int counterVowels = 0;
            Matcher m = p.matcher(word);
            while (m.find()) {
                counterVowels++;
            }
            double fractionVowels = (double) counterVowels / word.length();
            return fractionVowels;
        } else {
            return -2;
        }
    }
}
