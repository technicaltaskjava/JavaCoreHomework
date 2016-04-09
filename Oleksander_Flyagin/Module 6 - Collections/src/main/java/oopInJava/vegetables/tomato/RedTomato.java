package oopInJava.vegetables.tomato;

public class RedTomato extends Tomato
    {
        private String name = "Red Tomato";
        private int calories = 18;
        private int weight;

        public RedTomato(int weight)
            {
                this.weight = weight;
            }

        @Override
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
        public int getWeight()
            {
                return weight;
            }
    }
