package com.epam.javase06.task01.vegetables;

public class Vegetable implements Comparable<Vegetable> {
    private String name;
    private double weight; // in gram
    private int calorificValue; //calories per 100 grams of particular vegetable
    private double caloriesOfWeight;
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
    public String toString() {
        return name + " - " + weight + " grams";
    }
}
