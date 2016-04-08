package com.epam.task1.util;


import com.epam.task1.candy.Candy;

import java.util.Comparator;

/**
 * Created by Olga Kramska on 06-Mar-16.
 */
public class SortCandiesByWeight implements Comparator<Candy> {

    @Override
    public int compare(Candy o1, Candy o2) {
        Double w1 = o1.getWeight();
        Double w2 = o2.getWeight();
        return w1.compareTo(w2);
    }
}
