package com.epam.tasks0304javawords;

import java.io.*;

public class WordsFromText extends Words {

    public WordsFromText(String s) {
        super(s);
    }

    public void loadWords() {
        try (FileInputStream fileWords = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileWords, "UTF8");) {
            Reader reader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            int ch;
            boolean delimiter = false;
            while ((ch = reader.read()) > -1) {
                if (((char) ch >= 'a') && ((char) ch <= 'z')) {
                    buffer.append((char) ch);
                    delimiter = true;
                } else if (delimiter) {
                    buffer.append(',');
                    delimiter = false;
                }
            }
            String result = buffer.toString();
            words = result.split(",");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void writeWordsToFile(String newFileName) throws IOException {
        String currentJavaWords = compareWords();
        FileOutputStream myFile = new FileOutputStream(currentDir + newFileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
        out.write(currentJavaWords);
        out.close();
    }


}
