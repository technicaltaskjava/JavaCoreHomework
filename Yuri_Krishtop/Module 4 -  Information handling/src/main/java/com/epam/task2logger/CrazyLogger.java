package com.epam.task2logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yuriy Krishtop on 21.03.2016.
 */
public class CrazyLogger {


    private String fileName;

    public CrazyLogger() {
    }

    public CrazyLogger(String fileName) {
        this.fileName = "./src/main/java/com/epam/task2logger/" + fileName;
    }

    public void addLog(String message) {
        StringBuilder sb = new StringBuilder();
        if ((fileName == null) || (fileName == "")) {
            sb.append(message);
            System.out.println(sb.toString());
        } else {
            File file = new File(fileName);
            if (file.exists()) {
                sb.append(readLog());
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy : hh-mm");
            String date = sdf.format(new Date());
            sb.append(" " + date + " \u2014 " + message + " " + "\n");
            try {
                FileOutputStream myFile = new FileOutputStream(fileName);
                Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
                out.write(sb.toString());
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            return "File" + fileName + "did not read" + e.getMessage();
        }
    }

    public String findLog(String log) {
        String findLogs = "";
        String[] logs = readLog().split("\n");
        for (String l : logs) {
            if (l.matches(".+" + log + ".+")) {
                findLogs += l + "\n";
            }
        }
        return findLogs;
    }

}

