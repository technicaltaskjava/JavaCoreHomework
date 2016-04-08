package com.epam.task1.gift;

import com.epam.task1.candy.Candy;

import java.util.Comparator;
import java.util.List;


/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public interface IGift {

    List<Candy> getCandies();

    double getTotalWeight();

    boolean addCandy(Candy candy);

    List<Candy> sortCandies(Comparator<Candy> candyComparator);

    Candy findCandyOfMaxWeight();

}
