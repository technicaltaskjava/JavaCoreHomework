package com.epam.tasks0304javawords;

import java.io.*;

public class JavaWords extends Words {

    public JavaWords(String s) {
        super(s);
    }

    public void loadWords() {
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "UTF8");) {
            Reader reader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            int ch;
            while ((ch = reader.read()) > -1) {
                buffer.append((char) ch);
            }
            String result = buffer.toString();
            words = result.split(", ");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
