package com.epam.t03;

public class Pair<A,B> {
    private static Pair myInstance;
    private Unit firstPart;
    private B secondItem;

    private Pair(A firstItem, B secondItem) {
        this.secondItem = secondItem;
        this.firstPart = Unit.createUnit(firstItem);
    }

    public static <A,B> Pair createPair(A firstItem, B secondItem) {
        if (myInstance == null) {
            myInstance = new Pair(firstItem, secondItem);
        }
        return myInstance;
    }

    public String getFirstType() {
        return firstPart.getFirstItem().getClass().getName();
    }

    public String getSecondType() {
        return secondItem.getClass().getName();
    }

    public A getFirstItem() {
        return (A) firstPart.getFirstItem();
    }

    public B getSecondItem() {
        return secondItem;
    }

}
