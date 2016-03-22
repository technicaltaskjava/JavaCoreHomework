package com.epam.javase04.task03_htmlParser.reader;

import java.io.*;

public class HtmlReader {
    public StringBuilder readFile(String file) {
        File sampleFile = new File(file);
        StringBuilder allText = new StringBuilder();
        String string;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(sampleFile), "cp1251"));
            while ((string = reader.readLine()) != null) {
                allText.append(string).append("\r\n");
            }
            System.out.println("File \"" + sampleFile + "\" has read.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return allText;
    }
}
