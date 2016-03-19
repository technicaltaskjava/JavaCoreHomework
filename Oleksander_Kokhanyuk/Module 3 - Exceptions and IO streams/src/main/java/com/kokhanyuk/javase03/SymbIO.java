package com.kokhanyuk.javase03;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase03.actions.FindKeyWord;
import com.kokhanyuk.javase03.actions.MyDosOperation;
import com.kokhanyuk.javase03.actions.MyFileNotFound;

import java.io.File;

import static java.io.File.separator;

/**
 * SymbIO
 * <p/>
 * Reading and writing symbol information,
 * searching and counting the tokens in text.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SymbIO {

    public static void main(String[] arg) throws MyFileNotFound {
        String fileNameLook = "iodata" + separator + "simple.txt";
        String fileNameKey = "iodata" + separator + "keywords.txt";
        String fileNameOut = "iodata" + separator + "outSymb.txt";
        String[] keyWordsArr;
        StringBuilder outdata = new StringBuilder();
        int n;
        int m = 0;
        FindKeyWord fk = new FindKeyWord();
        keyWordsArr = FileIO.readSymbFile(fileNameKey).split("\\s");
        String whereLook = FileIO.readSymbFile(fileNameLook);

        for (String keyWord : keyWordsArr) {
            if (keyWord.length() > 1) {
                keyWord.trim();
                n = fk.foundKeyWord(whereLook, keyWord);
                if (n != 0) {
                    System.out.println(keyWord + " : " + n);
                    outdata.append(keyWord + " : " + n + " " + "\n");
                    m = m + n;
                }
            }
        }
        FileIO.writeSymbFile(fileNameOut, outdata.toString(), false);
        System.out.println("Total words found: " + m);
        System.out.println("Data wrote to: " + fileNameOut);
    }
}