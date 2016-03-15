package com.epam.javase03.task01.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public void readFile(File file) {
        BufferedReader reader = null;
        String string;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((string = reader.readLine()) != null) {
                System.out.println(string);
            }
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
    }
}
