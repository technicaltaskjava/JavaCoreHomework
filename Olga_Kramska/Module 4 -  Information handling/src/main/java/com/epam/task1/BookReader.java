package com.epam.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Kramska on 19-Mar-16.
 */
public class BookReader {
    private final static String MULTI_SPACE = "[ \u00A0\t]+";
    private final static String SENTENCE_SPLITTER = "[\n\\.\\?!…]";
    private final static String WORD = "[^à-ÿÀ-ß\\-]";

    private BookReader() {
    }

    public static String readBook(String fileName) {
        String readText;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        StringBuilder in = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    in.append(line).append("\n");
                }
            }
            readText = in.toString();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can not read file: " + fileName);
        }
        Pattern pattern = Pattern.compile(MULTI_SPACE);
        Matcher matcher = pattern.matcher(readText);
        return matcher.replaceAll(" ");
    }

    public static String[] getSentencesFromText(String text) {
        Pattern pattern = Pattern.compile(SENTENCE_SPLITTER);
        return pattern.split(text);
    }

    public static String[] getWordsFromText(String text) {
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(text);
        String textWithoutPunctuations = matcher.replaceAll(" ");
        return textWithoutPunctuations.split(" +");
    }
}
