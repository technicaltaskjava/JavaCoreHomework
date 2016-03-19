package com.kokhanyuk.javase03;

import com.kokhanyuk.javase03.actions.FileIO;
import com.kokhanyuk.javase03.actions.FindKeyWord;
import com.kokhanyuk.javase03.actions.MyFileNotFound;

import java.io.File;

import static java.io.File.separator;

/**
 * ByteIO
 * <p/>
 * Reading and writing byte information,
 * searching and counting the tokens in text.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ByteIO {

    public static void main(String[] arg) throws MyFileNotFound {
        String fileNameLook = "iodata" + separator + "simple.txt";
        String fileNameKey = "iodata" + separator + "keywords.txt";
        String fileNameOut = "iodata" + separator + "outByte.txt";
        String[] keyWordsArr;
        StringBuilder outdata = new StringBuilder();
        int n;
        int m = 0;
        FindKeyWord fk = new FindKeyWord();
        keyWordsArr = FileIO.readByteFile(fileNameKey).split("\\s");
        String whereLook = FileIO.readByteFile(fileNameLook);
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
        FileIO.writeByteFile(fileNameOut, outdata.toString());
        System.out.println("Total words found: " + m);
        System.out.println("Data wrote to: " + fileNameOut);
    }
}