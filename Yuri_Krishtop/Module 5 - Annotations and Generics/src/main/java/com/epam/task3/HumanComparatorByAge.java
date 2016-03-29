package com.epam.task3;

import java.util.Comparator;

/**
 * Created by Yuriy Krishtop on 28.03.2016.
 */
public class HumanComparatorByAge implements Comparator<Human> {

    public int compare(Human humanOne, Human humanTwo) {
        return humanOne.getAge() - humanTwo.getAge();
    }
}
