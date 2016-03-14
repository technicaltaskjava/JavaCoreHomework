package com.epam.task2.ingredient;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Pepper extends Vegetable {
    private static final double SPECIFIC_CALORIES = 26.5;

    public Pepper(double weight) {
        super(weight, SPECIFIC_CALORIES);
        name = "pepper";
    }
}
