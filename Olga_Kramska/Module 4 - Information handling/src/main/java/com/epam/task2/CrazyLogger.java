package com.epam.task2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Olga Kramska on 17-Mar-16.
 */
public class CrazyLogger {
    private StringBuilder logs = new StringBuilder();
    private OutputStream out;

    public CrazyLogger() {
        out = System.out;
    }

    public CrazyLogger(String fileName) {
        try {
            out = new FileOutputStream(fileName, true);
        } catch (IOException e) {
            System.out.println("Can not find the " + fileName + e.getMessage());
            out = System.out;
        }
    }

    public void log(String message) {
        LogRecord log = new LogRecord(message);
        logs.append(log);
        try {
            out.write(log.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Can not write log to the file" + e.getMessage());
        }
    }

    public String search(String text) {
        StringBuilder searchResult = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4} : [0-9]{2}-[0-9]{2} — .*" + text + ".*\\n");
        Matcher matcher = pattern.matcher(logs);
        int match = 0;
        while (matcher.find()) {
            searchResult.append(matcher.group());
            match++;
        }
        if (!searchResult.toString().isEmpty()) {
            System.out.println(match + " result(s) for \"" + text + "\" was found in the LOG:\n" + searchResult);
        } else {
            System.out.println("No results for \"" + text + "\" was found in the LOG");
        }
        return searchResult.toString();
    }
}

