package oopInJava.vegetables.cabbage;


import oopInJava.vegetables.Vegetables;

public  class Cabbage implements Vegetables
    {

        private String name = "Cabbage";
        private int calories = 11;
        private int weight;

        public Cabbage(int weight)
            {
                this.weight = weight;
            }


        public   String getVegetables()
            {
                return getName();
            }

        public String getName()
            {
                return name;
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

