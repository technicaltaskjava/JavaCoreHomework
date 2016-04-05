package com.epam.javase06.task01.chef;

import com.epam.javase06.task01.vegetables.Vegetable;
import com.epam.javase06.task01.vegetables.cabbages.GreenCabbage;
import com.epam.javase06.task01.vegetables.onions.DryOnion;
import com.epam.javase06.task01.vegetables.roots.Carrot;

import java.util.ArrayList;
import java.util.Collections;

public class Chef {
    ArrayList<Vegetable> salad = new ArrayList<>();

    public void createSalad() {
        salad.add(new GreenCabbage(300));
        salad.add(new Carrot(150));
        salad.add(new DryOnion(50));
        System.out.println("Salad's ingredients:\n" + salad);
    }


    public void sortByCaloriesOfWeight() {
        Collections.sort(salad);
        System.out.println("Sorted by ingredients by calories:");
        for(Vegetable v : salad) {
            System.out.println(v.getCaloriesOfWeight() + " calories of " + v.getName());
        }
    }
    public void countSaladCarories() {
        double calorificValue = 0;
        for(Vegetable v : salad) {
            calorificValue += v.getCaloriesOfWeight();
        }
        System.out.println("Salad calorific value is " + calorificValue + " calories.");
    }

    public void searchByVegetable(String vegetable) {
        boolean hasVegetables = false;
        for(Vegetable v : salad) {
            if (vegetable.toLowerCase().equals(v.getName().toLowerCase())) {
                hasVegetables = true;
            }
        }
        if(hasVegetables) {
            System.out.println("Ths salad contains " + vegetable);
        } else {
            System.out.println("The salad doesn't contain " + vegetable);
        }
    }
}
