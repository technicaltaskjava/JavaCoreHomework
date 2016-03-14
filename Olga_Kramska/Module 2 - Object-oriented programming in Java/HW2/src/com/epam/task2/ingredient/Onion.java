package com.epam.task2.ingredient;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Onion extends Vegetable {
    private static final double SPECIFIC_CALORIES = 41.0;

    public Onion(double weight) {
        super(weight, SPECIFIC_CALORIES);
        name = "onion";
    }
}
