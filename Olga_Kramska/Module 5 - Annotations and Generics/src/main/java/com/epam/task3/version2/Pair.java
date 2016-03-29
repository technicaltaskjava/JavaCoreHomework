package com.epam.task3.version2;

/**
 * Created by Olga Kramska on 28-Mar-16.
 */
public class Pair<T, U> {
    private T first;
    private U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair create(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
