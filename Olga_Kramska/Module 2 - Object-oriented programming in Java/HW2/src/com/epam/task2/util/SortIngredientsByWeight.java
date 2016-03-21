package com.epam.task2.util;

import com.epam.task2.ingredient.Vegetable;

import java.util.Comparator;

/**
 * Created by Olga Kramska on 06-Mar-16.
 */
public class SortIngredientsByWeight implements Comparator<Vegetable> {

    @Override
    public int compare(Vegetable vegetable1, Vegetable vegetable2) {
        if (vegetable1.getWeight() < vegetable2.getWeight()) {
            return -1;
        } else if (vegetable1.getWeight() > vegetable2.getWeight()) {
            return 1;
        } else {
            if (vegetable1.getCalories() < vegetable2.getCalories()) {
                return -1;
            } else if (vegetable1.getCalories() == vegetable2.getCalories()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
