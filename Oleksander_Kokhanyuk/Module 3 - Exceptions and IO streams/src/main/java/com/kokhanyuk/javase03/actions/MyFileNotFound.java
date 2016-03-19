package com.kokhanyuk.javase03.actions;

/**
 * MyFileNotFound
 * <p/>
 * This Exception is throws in a case where
 * the file not found.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyFileNotFound extends Exception {
    private String sE;

    MyFileNotFound(String id) {
        sE = id;
    }

    public String toString() {
        return "Error run program: file " + sE + " not found, program is close.";
    }
}
