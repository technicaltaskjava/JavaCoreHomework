package com.kokhanyuk.javase03.actions;

import java.util.Scanner;

/**
 * FindKeyWord
 * <p/>
 * This class keyword searches fo a String.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FindKeyWord {

    public int foundKeyWord(String whereLook, String keyWord) {
        char[] nextCharArr = new char[]{'.', ',', '(', ')', '{', '}', '[', ']', ':', ';'};
        int i = 0;
        String spl;
        Scanner split = new Scanner(whereLook);
        while (split.hasNext()) {
            spl = split.next();
            Scanner find = new Scanner(spl);
            if (find.findInLine(keyWord) != null) {
                if (find.hasNext()) {
                    char[] ch = find.next().toCharArray();
                    for (char nextChar : nextCharArr) {
                        if (ch.length != 0) {
                            if (ch[0] == nextChar) {
                                if (spl.indexOf(keyWord) == 0) {
                                    i++;
                                } else {
                                    if (spl.indexOf(keyWord) > 0) {
                                        char[] foundSomeone = spl.toCharArray();
                                        for (char nextNewChar : nextCharArr) {
                                            if (foundSomeone[spl.indexOf(keyWord) - 1] == nextNewChar) {
                                                i++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (spl.length() == keyWord.length()) {
                        i++;
                    }
                }
            }
        }
        split.close();
        return i;
    }
}


