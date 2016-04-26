package com.epam.task1;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yuriy Krishtop on 22.04.2016.
 */
public class LoggerSingleton {

    private static final Logger log = Logger.getLogger(LoggerSingleton.class);
    private String fileName;
    private static volatile LoggerSingleton loggerSingleton;

    private LoggerSingleton() {
        fileName = "src/main/resources/log.txt";
    }

    public static LoggerSingleton getLoggerSingleton() {
        if (loggerSingleton == null)
            synchronized (LoggerSingleton.class) {
                if (loggerSingleton == null) {
                    loggerSingleton = new LoggerSingleton();
                }
            }
        return loggerSingleton;
    }

    public void log(Object obj) {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error(e);
            }
        } else {
            sb.append(readLog());
        }
        SimpleDateFormat sdf = new SimpleDateFormat(" dd-M-yyyy : hh-mm-ss - ");
        String date = sdf.format(new Date());
        sb.append(date).append(obj.toString()).append(" ").append("\n");
        try {
            FileOutputStream myFile = new FileOutputStream(fileName);
            Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
            out.write(sb.toString());
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String readLog() {
        String text = "";
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "utf8");) {
            BufferedReader inFile = new BufferedReader(inputStreamReader);
            String currentLine;
            while ((currentLine = inFile.readLine()) != null) {
                text += currentLine + "\n";
            }
            return text;
        } catch (IOException e) {
            log.error(e);
            return "Error";
        }
    }

    public String findLog(String message) {
        String findLogs = "";
        String[] logs = readLog().split("\n");
        for (String l : logs) {
            if (l.matches(".+" + message + ".+")) {
                findLogs += l + "\n";
            }
        }
        return findLogs;
    }

}
