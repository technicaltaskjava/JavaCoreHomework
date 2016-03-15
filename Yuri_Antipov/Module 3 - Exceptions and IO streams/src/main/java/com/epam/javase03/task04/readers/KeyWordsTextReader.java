package com.epam.javase03.task04.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KeyWordsTextReader {
    public String[] createKeyWordList() {
        File keywordsFile = new File("src/JavaKeywords.txt");
        String[] listOfKeywords = new String[0];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(keywordsFile));
            String keyword;
            int index = 0;
            while ((keyword = reader.readLine()) != null) {
                String[] tmp = listOfKeywords;
                listOfKeywords = new String[tmp.length + 1];
                listOfKeywords[index] = keyword;
                System.arraycopy(tmp,0,listOfKeywords,0,tmp.length);
                index++;
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
        return listOfKeywords;
    }
}
