package com.epam.t02;

import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrazyLogger {
    private StringBuilder logData;
    private static final String LOG_SPLIT_REGEX = "[\\d]{2}-[\\d]{2}-[\\d]{4}\\ :\\ [\\d]{2}-[\\d]{2}\\ -\\ .*";

    public CrazyLogger() {
        this.logData = new StringBuilder();
    }

    public void addLogData(String newLine) {
        Formatter dataFormatter = new Formatter();
        long currentTime = System.currentTimeMillis();
        String currentDate = new SimpleDateFormat("dd-MM-YYYY : HH-mm - ").format(currentTime);
        this.logData.append(currentDate).append(newLine).append(System.lineSeparator());
    }

    public void outFullLogData() {
        System.out.println(this.logData);
    }

    public void searchLogData(String searchData, int searchType) {
        String resultData="";
        int matchesCount = 0;
        switch (searchType) {
            case 1: {
                searchData += " : ";
                break;
            }
            case 2: {
                searchData += " - ";
                break;
            }
        }

        Pattern sentencePattern = Pattern.compile(LOG_SPLIT_REGEX);
        Matcher sentenceMatcher = sentencePattern.matcher(this.logData.toString());
        while (sentenceMatcher.find()) {
            String matchesData = sentenceMatcher.group();
            if (matchesData.contains(searchData)) {
                matchesCount++;
                resultData = resultData + sentenceMatcher.group() + System.lineSeparator();
            }
        }
        if (matchesCount == 0) {
            resultData="No any matches...";
        }
        System.out.println(resultData);
    }
}
