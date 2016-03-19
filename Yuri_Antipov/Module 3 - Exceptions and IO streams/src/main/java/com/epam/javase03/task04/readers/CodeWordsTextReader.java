package com.epam.javase03.task04.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CodeWordsTextReader {
    public String[] createCodeWordsList() {
        File codeFile = new File("src/Employee.java");
        StringBuilder builder = new StringBuilder();
        String[] codeWordsList = new String[1];
        String string;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(codeFile));
            while ((string = reader.readLine()) != null) {
                codeWordsList = string.split("\\s+");
                for (int i = 0; i < codeWordsList.length; i++) {
                    builder.append(codeWordsList[i]).append("\n");
                }
                codeWordsList = builder.toString().split("[ +-/*=\\t\\n\\x0B\\f\\r;,.(){}]+");
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
        return codeWordsList;
    }
}
