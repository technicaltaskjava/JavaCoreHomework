package edu.task3;

/**
 * Created by Oleg on 27.03.2016.
 */
public class Run {

    private Run() {
    }

    public static void main(String[] args) {

        Unit<Integer> unit = new Unit<>(1);
        System.out.println(unit);

        Pair<Integer, String> pair = new Pair<>(1, "two");
        System.out.println(pair);

        Triplet<Integer, String, Integer> triplet = new Triplet<>(1, "second", 3);
        System.out.println(triplet);

    }

}
