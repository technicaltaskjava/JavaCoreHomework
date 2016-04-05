package oopInJava;

import oopInJava.salad.Salad;
import oopInJava.vegetables.cabbage.PeTsai;
import oopInJava.vegetables.cucuber.Cucumber;
import oopInJava.vegetables.tomato.RedTomato;
import oopInJava.vegetables.tomato.YellowTomato;

public class Cook
    {
        private Cook()
            {
            }

        public static void main(String[] args)
            {
                Salad salad =new Salad();
                salad.creatSald( new Cucumber(70));
                salad.creatSald( new PeTsai(100));
                salad.creatSald(new YellowTomato(50));
                salad.creatSald(new RedTomato(50));
                salad.showVegetables();
                System.out.println("---");
                salad.sortwVegetables();
                salad.showVegetables();
                System.out.println("---");
                salad.getVegetable();
                System.out.println("---");
                salad.showVegetablesWeight();
                salad.allVegetablesWeight();
            }

    }
