package com.epam.task2.ingredient;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Tomato extends Vegetable {
    private static final double SPECIFIC_CALORIES = 23.0;

    public Tomato(double weight) {
        super(weight, SPECIFIC_CALORIES);
        name = "tomato";
    }
}
