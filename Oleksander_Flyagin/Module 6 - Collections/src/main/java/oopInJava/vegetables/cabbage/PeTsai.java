package oopInJava.vegetables.cabbage;



public class PeTsai extends Cabbage
    {
        private String name = "Pe-tsai";
        private int calories = 12;

        public PeTsai(int weight)
            {
                super(weight);
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


    }
