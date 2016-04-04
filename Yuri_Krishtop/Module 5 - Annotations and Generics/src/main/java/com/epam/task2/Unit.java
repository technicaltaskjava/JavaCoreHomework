package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 27.03.2016.
 */
public class Unit<A> {
    private final A first;

    private Unit(A first) {
        this.first = first;
    }

    public static <A> Unit<A> create(A first) {
        return new Unit<A>(first);
    }

    public A getFirst() {
        return first;
    }

    public String toString() {
        return "First: " + first;
    }

}
