package oopInJava.vegetables.cabbage;


import oopInJava.vegetables.Vegetables;

public  class Cabbage implements Vegetables
    {

        private  String name = "Cabbage";
        private  int calories = 11;

        private int weight;

        public Cabbage(int weight)
            {
                this.weight = weight;
            }


        @Override
        public   String getVegetables()
            {
                return getName();
            }



        public String getName()
            {
                return name;
            }


        @Override
        public int getCalories()
            {
                return calories;
            }


        @Override
        public int getWeight(){
                return weight;
            }
    }

