package com.epam.task2.ingredient;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Cucumber extends Vegetable {
    private static final double SPECIFIC_CALORIES = 14.5;

    public Cucumber(double weight) {
        super(weight, SPECIFIC_CALORIES);
        name = "cucumber";
    }
}
