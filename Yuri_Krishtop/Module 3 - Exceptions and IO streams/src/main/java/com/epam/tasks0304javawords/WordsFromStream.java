package com.epam.tasks0304javawords;

import java.io.*;

public class WordsFromStream extends Words {

    public WordsFromStream(String s) {
        super(s);
    }

    public void loadWords() {
        try (FileInputStream fileWords = new FileInputStream(fileName);
             BufferedInputStream buffer = new BufferedInputStream(fileWords)) {
            boolean eof = false;
            String wordsFromStream = "";
            boolean delimiter = false;
            while (!eof) {
                int byteValue = buffer.read();
                if (((char) byteValue >= 'a') && ((char) byteValue <= 'z')) {
                    wordsFromStream += (char) byteValue;
                    delimiter = true;
                } else if (delimiter) {
                    wordsFromStream += ",";
                    delimiter = false;
                }
                if (byteValue == -1)
                    eof = true;
            }
            words = wordsFromStream.split(",");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void writeWordsToFile(String newFileName) throws IOException {
        byte[] currentJavaWords = compareWords().getBytes();
        FileOutputStream myFile = new FileOutputStream(currentDir + newFileName);
        BufferedOutputStream out = new BufferedOutputStream(myFile);
        out.write(currentJavaWords);
        out.close();
    }

}