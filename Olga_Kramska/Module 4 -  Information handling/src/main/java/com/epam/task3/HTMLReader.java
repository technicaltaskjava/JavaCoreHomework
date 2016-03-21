package com.epam.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Kramska on 20-Mar-16.
 */
public class HTMLReader {
    private final static String SENTENCE_WITH_IMAGE_LINKS =
            "([ј-я][^\\.\\?]*?((\\([–р]ис\\. [0-9].*?\\))|([Ќн]а рисунк)).*?[\\.\\?!] )[ј-я]";

    private String text;

    public HTMLReader(String fileName, String encoding) {
        String content = readFile(fileName, encoding);
        parseBody(getBodyOfHTML(content));
    }

    public void printSentenceWithImageLink() {
        Pattern pattern = Pattern.compile(SENTENCE_WITH_IMAGE_LINKS);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    private String readFile(String fileName, String encoding) {
        String readText;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        StringBuilder in = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(fileName), encoding))) {
            String line;
            while ((line = reader.readLine()) != null) {
                in.append(line);
            }
            readText = in.toString();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can not read file: " + fileName);
        }
        return readText;
    }

    private String getBodyOfHTML(String content) {
        Pattern pattern = Pattern.compile("<body>(.+)</body>");
        Matcher matcher = pattern.matcher(content);
        StringBuilder body = new StringBuilder();
        while (matcher.find()) {
            body.append(matcher.group(1));
        }
        return body.toString();
    }

    private void parseBody(String body) {
        String[] textWithoutServiceDigits = body.split("(<.+?>)|(&nbsp)|(;)");
        StringBuilder pureText = new StringBuilder();
        for (String text : textWithoutServiceDigits) {
            pureText.append(text).append(" ");
        }
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(pureText.toString());
        this.text = matcher.replaceAll(" ");
    }
}
