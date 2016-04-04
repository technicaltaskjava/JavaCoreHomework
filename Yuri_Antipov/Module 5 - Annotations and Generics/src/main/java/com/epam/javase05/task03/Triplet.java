package com.epam.javase05.task03;

public class Triplet<A,B,C> extends Pair<A,B> {
    private C value3;

    public Triplet(A value1, B value2, C value3) {
        super(value1,value2);
        this.value3 = value3;
    }

    public C getValue3() {
        return value3;
    }

    public String toString() {
        return "Value #1 - \"" + getValue1() +"\", value #2 - \"" + getValue2() + "\", value #3 - \"" + value3 + "\".";
    }
}
