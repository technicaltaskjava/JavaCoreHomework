package com.epam.javase06.task01.vegetables;

import java.util.Objects;

public class Vegetable implements Comparable<Vegetable> {
    private String name;
    private double weight; // in gram
    private int calorificValue; //calories per 100 grams of particular vegetable
    private String vegetableType;

    public Vegetable(String name, int weight, int calorificValue, String vegetableType) {
        this.name = name;
        this.weight = weight;
        this.calorificValue = calorificValue;
        this.vegetableType = vegetableType;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getCalorificValue() {
        return calorificValue;
    }

    public double getCaloriesOfWeight() {
        return weight / 100 * calorificValue;
    }

    public String getVegetableType() {
        return vegetableType;
    }

    @Override
    public int compareTo(Vegetable vegetable) {
        return (int) (vegetable.getWeight() - weight);
    }
    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Vegetable other = (Vegetable) otherObject;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, calorificValue, vegetableType);
    }

    @Override
    public String toString() {
        return name + " - " + weight + " grams";
    }
}
