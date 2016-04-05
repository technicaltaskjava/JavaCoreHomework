package com.kokhanyuk.collections.nombersstorage;

import java.util.LinkedList;

/**
 * NumbersStorage
 *
 * This class implements a storage structure of numbers.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class NumbersStorage {
    private LinkedList<Number> num = new LinkedList();

    public void addNumber(Number n) {
        num.add(n);
    }

    public void addNumber(int index, Number n) {
        if (index <= num.size()) {
            num.add(index, n);
        }
    }

    public Number getNumber(int index) {
        if (index <= num.size()) {
            return num.get(index);
        }
        return null;
    }

    public boolean remNumber(Number n) {
        return num.remove(n);
    }

    public void remNumber(int index) {
        if (index <= num.size()) {
            num.remove(index);
        }
    }

    public int getSize() {
        return num.size();
    }

    @Override
    public String toString() {
        return num.toString();
    }
}
