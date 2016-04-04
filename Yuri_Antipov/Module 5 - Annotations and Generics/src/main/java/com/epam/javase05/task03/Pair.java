package com.epam.javase05.task03;

public class Pair<A,B> extends Unit<A> {
    private B value2;

    public Pair(A value1, B value2) {
        super(value1);
        this.value2 = value2;
    }

    public B getValue2() {
        return value2;
    }

    public String toString() {
       return "Value #1 - \"" + getValue1() + "\", value #2 - " + value2 + "\".";
    }
}
