package com.epam.task2;

import com.epam.task2.ingredient.*;
import com.epam.task2.salad.Salad;
import com.epam.task2.util.SortIngredientsByCalories;
import com.epam.task2.util.SortIngredientsByWeight;

import java.util.Arrays;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Vegetable[] vegetables = {new Tomato(150), new Cucumber(100), new Pepper(50), new Onion(50)};
        Salad salad = new Salad(vegetables);
        System.out.println(salad);

        double totalCalories = salad.calcCalories();
        System.out.println("Calorie content of a salad is " + totalCalories + " kcal");

        Vegetable ingredientWithMaxCalories = salad.findIngredientWithMaxCalories();
        System.out.println("The most caloric ingredient in the salad is " + ingredientWithMaxCalories);
        System.out.println(" ");

        Vegetable[] sortedSalad1 = salad.sortIngredients(new SortIngredientsByCalories());
        System.out.println(Arrays.toString(sortedSalad1));

        Vegetable[] sortedSalad2 = salad.sortIngredients(new SortIngredientsByWeight());
        System.out.println(Arrays.toString(sortedSalad2));
    }
}
