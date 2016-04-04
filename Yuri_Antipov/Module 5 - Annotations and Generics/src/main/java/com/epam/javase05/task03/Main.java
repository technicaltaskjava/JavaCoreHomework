package com.epam.javase05.task03;

public class Main {
    public static void main(String[] args) {
        Unit unit = new Unit("Something");
        System.out.println(unit.toString());

        Pair pair = new Pair(848, "Code");
        System.out.println(pair.toString());

        Triplet triplet = new Triplet("The sun", "always","rises");
        System.out.println(triplet.toString());
    }
}
