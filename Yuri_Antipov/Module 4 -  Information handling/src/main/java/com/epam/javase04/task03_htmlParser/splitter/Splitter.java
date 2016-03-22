package com.epam.javase04.task03_htmlParser.splitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {
    private Pattern pattern = Pattern.compile("img.*(src.*(jpg|png|gif))");

    public void splitToSentence(StringBuilder text) {
        Matcher m = pattern.matcher(text);
        while(m.find()) {
            System.out.println(m.group(1));
        }
    }
}
