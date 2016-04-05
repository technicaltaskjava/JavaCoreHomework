package oopInJava.vegetables.tomato;


import oopInJava.vegetables.Vegetables;

public abstract class Tomato implements Vegetables
    {

        public   String getVegetables()
            {
                return getName();
            }

        public abstract String getName();
        public  abstract int getCalories();
        public abstract int getWeight();

    }
