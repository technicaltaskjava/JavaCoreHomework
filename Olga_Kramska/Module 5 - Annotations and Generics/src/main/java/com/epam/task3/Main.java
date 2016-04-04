package com.epam.task3;

import com.epam.task3.version1.Pair;
import com.epam.task3.version1.Triplet;

/**
 * Created by Olga Kramska on 26-Mar-16.
 */
public class Main {
    public static void main(String[] args) {
        Pair<Integer, Double> pair = new Pair<>(25, 170.3);
        System.out.println(pair.first + " "+ pair.second);

        com.epam.task3.version2.Pair pair2 = com.epam.task3.version2.Pair.create(15, 17.8);
        System.out.println(pair2.getFirst() + " " + pair2.getSecond());

        Triplet<Long, String, Double> triplet = new Triplet<>(10000000L, "ku", 24.4);
        System.out.println(triplet.first + " "+ triplet.second +" "+ triplet.third);
    }
}
