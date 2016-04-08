package com.epam.task1.gift;

import com.epam.task1.candy.Candy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Gift implements IGift {
    private List<Candy> candies;

    public Gift(List<Candy> candies) {
        this.candies = candies;
    }

    public Gift() {
        candies = new ArrayList<>();
    }

    @Override
    public List<Candy> getCandies() {
        return candies;
    }

    @Override
    public double getTotalWeight() {
        double totalWeight = 0;
        for (Candy candy : candies) {
            totalWeight += candy.getWeight();
        }
        return totalWeight;
    }

    @Override
    public boolean addCandy(Candy candy) {
        candies.add(candy);
        return true;
    }

    @Override
    public List<Candy> sortCandies(Comparator<Candy> candyComparator) {
        List<Candy> sortedCandies = new ArrayList<>();
        sortedCandies.addAll(candies);
        sortedCandies.sort(candyComparator);
        return sortedCandies;
    }

    @Override
    public Candy findCandyOfMaxWeight() {
        Candy candyOfMaxWeight = candies.get(0);
        for (int i = 1; i < candies.size(); i++) {
            if (candies.get(i).getWeight() > candyOfMaxWeight.getWeight()) {
                candyOfMaxWeight = candies.get(i);
            }
        }
        return candyOfMaxWeight;
    }

    @Override
    public String toString() {
        return "Gift" + candies;
    }
}

