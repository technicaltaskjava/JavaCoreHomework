package com.epam.task2.ingredient;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public abstract class Vegetable implements Cloneable {
    protected double calories;
    protected double weight;
    protected String name;

    public Vegetable(double weight, double calories1) {
        this.weight = weight;
        calories = weight * calories1 / 100;
    }

    public double getCalories() {
        return calories;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + weight + " g " + calories + " kcal";
    }

    @Override
    public Vegetable clone() throws CloneNotSupportedException {
        Vegetable obj = (Vegetable) super.clone();
        obj.calories = this.calories;
        obj.weight = this.weight;
        obj.name = this.name;
        return obj;
    }
}
