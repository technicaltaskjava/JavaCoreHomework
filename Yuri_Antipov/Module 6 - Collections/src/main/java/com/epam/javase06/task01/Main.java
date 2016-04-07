package com.epam.javase06.task01;

import com.epam.javase06.task01.chef.Chef;

public class Main {
    private Main(){}

    public static void main(String[] args) {
        Chef chef = new Chef();
        chef.createSalad();
        chef.sortByCaloriesOfWeight();
        chef.countSaladCarories();
        chef.searchByVegetable("carrot");
        chef.searchByVegetable("tomato");
    }
}
