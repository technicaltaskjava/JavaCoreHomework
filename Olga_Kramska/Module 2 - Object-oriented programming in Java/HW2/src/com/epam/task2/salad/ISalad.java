package com.epam.task2.salad;

import com.epam.task2.ingredient.Vegetable;

import java.util.Comparator;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public interface ISalad {

    double calcCalories();

    Vegetable[] sortIngredients(Comparator<Vegetable> vegetableComparator) throws CloneNotSupportedException;

    Vegetable findIngredientWithMaxCalories();

}
