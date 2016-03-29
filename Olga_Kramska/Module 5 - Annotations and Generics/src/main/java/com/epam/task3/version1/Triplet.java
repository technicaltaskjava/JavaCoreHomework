package com.epam.task3.version1;

/**
 * Created by Olga Kramska on 25-Mar-16.
 */
public class Triplet<S, T, U> extends Pair<S, T> {
    public final U third;

    public Triplet(S first, T second, U third) {
        super(first, second);
        this.third = third;
    }
}
