package com.epam.task3.version2;

/**
 * Created by Olga Kramska on 28-Mar-16.
 */
public class Triplet<S, T, U> {
    private S first;
    private T second;
    private U third;

    private Triplet(S first, T second, U third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <S, T, U> Triplet create(S first, T second, U third) {
        return new Triplet<>(first, second, third);
    }

    public S getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public U getThird() {
        return third;
    }
}
