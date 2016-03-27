package javase04.t01.textbook;

import javase04.common.handler.FileHandler;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TextBook handling class
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class TextBook extends FileHandler {
    /**
     * swap first and last word for every sentence.
     */
    public void methodA() {
        Matcher matcher = Pattern.compile("[А-ЯA-Z](\\d\\.|[^\\.!\\?])+[\\.!\\?]").matcher(content.toString());
        Pattern patternWord = Pattern.compile("[А-Яа-я\\w]+");
        while (matcher.find()) {
            Matcher matcherWord = patternWord.matcher(matcher.group());
            int indexFirstWord = -1;
            int indexLastWord = -1;
            String firstWord = "";
            String lastWord = "";
            while (matcherWord.find()) {
                if (indexFirstWord == -1) {
                    indexFirstWord = matcher.start() + matcherWord.start();
                    firstWord = matcherWord.group();
                }
                indexLastWord = matcher.start() + matcherWord.start();
                lastWord = matcherWord.group();
            }
            if (indexFirstWord == -1) {
                continue;
            }
            content.replace(indexLastWord, indexLastWord + lastWord.length(), firstWord);
            content.replace(indexFirstWord, indexFirstWord + firstWord.length(), lastWord);
        }
        removeExtraSpacesAndTabs();
    }

    /**
     * sort all the words by part of vowel letters
     */
    public void methodB() {
        class WordPart implements Comparable<WordPart> {
            String word;
            double part;

            WordPart(String word, double part) {
                this.word = word;
                this.part = part;
            }

            public int compareTo(WordPart o) {
                return Double.compare(part, o.part);
            }
        }

        String[] words = content.toString().split("[^А-Яа-яA-Za-z]+");
        double[] parts = new double[words.length];

        WordPart[] wordParts = new WordPart[words.length];

        Pattern pattern = Pattern.compile("[АЕЁИОУЫЭЮЯаеёиоуыэюяAEIOUaeiou]");
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            Matcher matcher = pattern.matcher(word);
            int vowelCount = 0;
            while (matcher.find()) {
                vowelCount++;
            }
            wordParts[index] = new WordPart(word, vowelCount*1.0/word.length());
        }

        Arrays.sort(wordParts);
        clear();
        for (WordPart wordPart : wordParts) {
            content.append(wordPart.word);
            content.append("\r\n");
        }
    }

    /**
     * delete all the words of given length, starting with consonant letter
     */
    public void methodC(int wordLength) {
        wordLength = (wordLength > 1) ? wordLength : 1;
        int offset = 0;
        Matcher matcher = Pattern
                .compile("\\s+([Б-ДЖЗК-НП-ТФ-Щб-джзк-нп-тф-щB-DF-HJ-NQ-TV-Zb-df-hj-nq-tv-z][А-Яа-я\\w]{"
                        + (wordLength -1) + "})\\s+")
                .matcher(content.toString());
        while (matcher.find()) {
            content.delete(matcher.start(1) - offset,
                    matcher.start(1) + wordLength - offset);
            offset += wordLength;
        }
        removeExtraSpacesAndTabs();
    }

    /**
     * delete all next occurrences of first letter for every words
     */
    public void methodD() {
        Locale localRu = new Locale("ru", "RU");
        int offset = 0;
        Matcher matcher = Pattern.compile("[А-Яа-я\\w]+").matcher(content.toString());
        while (matcher.find()) {
            String word = matcher.group();
            String firstLetter = word.substring(0, 1);
            word = firstLetter + word.substring(1).replace(firstLetter.toLowerCase(localRu), "");
            int deltaLength = matcher.group().length() - word.length();
            if (deltaLength > 0) {
                content.replace(matcher.start() - offset,
                        matcher.start() - offset + matcher.group().length(), word);
                offset += deltaLength;
            }
        }
        removeExtraSpacesAndTabs();
    }

    private void removeExtraSpacesAndTabs() {
        replaceAll("\t", " ");
        while (content.indexOf("  ") != -1) {
            replaceAll("  ", " ");
        }
    }

    private void replaceAll(String from, String to) {
        int index = content.indexOf(from);
        while (index != -1)
        {
            content.replace(index, index + from.length(), to);
            index += to.length();
            index = content.indexOf(from, index);
        }
    }
}
