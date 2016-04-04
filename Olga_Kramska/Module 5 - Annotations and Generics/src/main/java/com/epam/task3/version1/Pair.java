package com.epam.task3.version1;

/**
 * Created by Olga Kramska on 25-Mar-16.
 */
public class Pair<T, U> extends Unit<T> {
    public final U second;

    public Pair(T first, U second) {
        super(first);
        this.second = second;
    }
}
