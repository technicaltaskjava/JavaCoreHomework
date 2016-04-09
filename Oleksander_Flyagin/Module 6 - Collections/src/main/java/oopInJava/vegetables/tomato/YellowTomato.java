package oopInJava.vegetables.tomato;

public class YellowTomato extends Tomato
    {

        private String name = "Yellow Tomato";
        private int calories = 15;
        private int weight;

        public YellowTomato(int weight)
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


