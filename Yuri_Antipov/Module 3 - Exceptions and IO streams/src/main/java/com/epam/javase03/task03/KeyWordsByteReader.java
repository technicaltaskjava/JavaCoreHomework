package com.epam.javase03.task03;

import java.io.*;

public class KeyWordsByteReader {
    public String[] createKeyWordList() {
        File keyWordsFile = new File("src/JavaKeywords.txt");
        String[] listOfKeywords = new String[1];
        try {
            DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(keyWordsFile)));
            String keyword = null;
            int index = 0;
            while (reader.available() != 0) {
                reader.readChar();
                listOfKeywords[index] = keyword;
                String[] tmp = listOfKeywords;
                listOfKeywords = new String[tmp.length + 1];
                System.arraycopy(tmp,0,listOfKeywords,0,tmp.length);
                index++;
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String[] tmp = listOfKeywords;
        listOfKeywords = new String[tmp.length - 1];
        System.arraycopy(tmp,0,listOfKeywords,0,listOfKeywords.length);
        return listOfKeywords;
    }
}
