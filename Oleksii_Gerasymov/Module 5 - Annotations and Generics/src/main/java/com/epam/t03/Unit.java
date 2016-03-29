package com.epam.t03;

public class Unit<A> {
    private static Unit myInstance;
    private A firstItem;

    private Unit(A firstItem) {
        this.firstItem = firstItem;
    }

    public static <A> Unit createUnit(A firstItem) {
        if (myInstance == null) {
            myInstance = new Unit(firstItem);
        }
        return myInstance;
    }

    public String getFirstType() {
        return firstItem.getClass().getName();
    }

    public A getFirstItem() {
        return firstItem;
    }
}
