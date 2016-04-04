package com.epam.javase05.task03;

public class Unit<A> {
    private A value1;

    public Unit(A value1) {
        this.value1 = value1;
    }

    public A getValue1() {
        return value1;
    }

    public String toString() {
        return "Value #1 - \"" + value1 + "\".";
    }
}
