package com.epam.task1.util;

import com.epam.task1.candy.Candy;

import java.util.Comparator;

/**
 * Created by Olga Kramska on 06-Mar-16.
 */
public class SortCandiesByName implements Comparator<Candy> {

    @Override
    public int compare(Candy o1, Candy o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
