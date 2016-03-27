package com.epam.javase04.task02_crazyLogger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrazyLogger {
    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy:hh-mm");
    private StringBuilder logsKeeper = new StringBuilder();

    public void addLog(String massage) {
        String massageDate = dateFormat.format(cal.getTime());
        logsKeeper.append(massageDate).append(" - " + massage).append("\r\n");
    }

    public void viewLogs() {
        System.out.println(logsKeeper);
    }

    public void findLog(String searchSample) {
        Pattern pattern = Pattern.compile(".*" + searchSample.toLowerCase() + ".*\\r\\n");
        Matcher matcher = pattern.matcher(logsKeeper.toString().toLowerCase());
        StringBuilder foundLog = new StringBuilder("");

        int searchCount = 0;
        while (matcher.find()) {
            searchCount++;
            foundLog.append(searchCount + ") ").append(matcher.group());
        }
        if(searchCount != 0 && !searchSample.equals("")) {
            System.out.println("Have found " + searchCount +  " log(s) by the search criteria \"" + searchSample + "\":");
            System.out.println(foundLog);
        } else {
            System.out.println("Log have not found by the search criteria \"" + searchSample + "\".\n");
        }

    }
}
