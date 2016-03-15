package com.epam.javase03.task03;

import java.io.*;

public class TestReadAndWriteChar {
    public static void main(String[] args) {
        File keyWordsFile = new File("src/JavaKeywords.txt");
        File writtenWordsFile = new File("TestWriting.txt");
        char[] chars = new char[0];
        char[] tmp;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(keyWordsFile));
            out = new BufferedOutputStream(new FileOutputStream(writtenWordsFile));
            int b;
            int index = 0;
            while((b = in.read()) != -1) {
                tmp = chars;
                chars = new char[tmp.length + 1];
                chars[index] = (char) b;
                System.arraycopy(tmp,0,chars,0,tmp.length);
                out.write(b);
                index++;
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (char b : chars) {
            System.out.print(b);
        }

        StringBuilder builder = new StringBuilder();
        String string;
        String[] wordList = chars.toString().split("\\s+");
        for (String word : wordList) {
            System.out.println(word);
        }
    }
}
