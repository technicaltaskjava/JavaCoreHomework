package oopInJava.vegetables.cucuber;


import oopInJava.vegetables.Vegetables;

public class Cucumber implements Vegetables
    {
        private int weight;
        private int calories = 16;

        public Cucumber(int weight)
            {
                this.weight = weight;
            }

        public String getVegetables()
            {
                return "Cucuber";
            }

        public int getCalories()
            {
                return calories;
            }

        public int getWeight()
            {
                return weight;
            }
    }
