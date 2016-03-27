package com.epam.javase04.task01_bookHandler.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class dataReader {
    public StringBuilder readFile(String file) {
        File sampleFile = new File(file);
        StringBuilder allText = new StringBuilder();
        String string;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sampleFile));
            while ((string = reader.readLine()) != null) {
                allText.append(string).append("\n");
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
