package com.kokhanyuk.javase05;

import java.util.Arrays;
import java.util.Comparator;

/**
 * CompareUtils
 *
 * This class implements the ability to search in a specified array of elements.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CompareUtils<T extends Comparable> {
    private T rez;

    public T max(T[] arg) {
        Arrays.sort(arg);
        rez = arg[arg.length - 1];
        return rez;
    }

    public T max(T[] arg, Comparator<T> comp) {
        Arrays.sort(arg, comp);
        rez = arg[arg.length - 1];
        return rez;
    }

    public T mid(T[] arg) {
        Arrays.sort(arg);
        rez = arg[(arg.length - 1) / 2];
        return rez;
    }

    public T mid(T[] arg, Comparator<T> comp) {
        Arrays.sort(arg, comp);
        rez = arg[(arg.length - 1) / 2];
        return rez;
    }

    public T min(T[] arg) {
        Arrays.sort(arg);
        rez = arg[0];
        return rez;
    }

    public T min(T[] arg, Comparator<T> comp) {
        Arrays.sort(arg, comp);
        rez = arg[0];
        return rez;
    }
}
