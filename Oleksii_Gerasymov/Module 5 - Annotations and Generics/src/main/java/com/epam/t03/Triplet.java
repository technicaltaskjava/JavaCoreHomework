package com.epam.t03;

public class Triplet<A, B, C> {
    private static Triplet myInstance;
    private Pair firstPart;
    private C thirdItem;

    private Triplet(A firstItem, B secondItem, C thirdItem) {
        this.thirdItem = thirdItem;
        this.firstPart = Pair.createPair(firstItem,secondItem);
    }

    public static <A,B,C> Triplet createTriplet(A firstItem, B secondItem, C thirdItem) {
        if (myInstance == null) {
            myInstance = new Triplet(firstItem, secondItem, thirdItem);
        }
        return myInstance;
    }

    public String getFirstType() {
        return firstPart.getFirstItem().getClass().getName();
    }

    public String getSecondType() {
        return firstPart.getSecondItem().getClass().getName();
    }

    public String getThirdType() {
        return thirdItem.getClass().getName();
    }

    public A getFirstItem() {
        return (A) firstPart.getFirstItem();
    }

    public B getSecondItem() {
        return (B) firstPart.getSecondItem();
    }

    public C getThirdItem() {
        return thirdItem;
    }
}
