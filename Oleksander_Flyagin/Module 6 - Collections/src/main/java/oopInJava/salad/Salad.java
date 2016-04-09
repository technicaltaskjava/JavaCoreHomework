package oopInJava.salad;



import oopInJava.vegetables.Vegetables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Salad
    {

        private ArrayList<Vegetables> sald = new ArrayList<Vegetables>();

        public void creatSald(Vegetables vegetables)
            {
                sald.add(vegetables);

            }

        public void showVegetables()
            {
                for (Vegetables vegetables: sald)
                    {
                        System.out.println(vegetables.getVegetables() + " calories : " + vegetables.getCalories());
                    }
            }

        public void showVegetablesWeight()
            {
                for (Vegetables vegetables: sald)
                    {
                        System.out.println(vegetables.getVegetables() + " Weight = " + vegetables.getWeight());
                    }
            }

        public void sortwVegetables()
            {
                sald.sort(new MyComparator());
            }

        public void getVegetable()
            {
                String vagetable =null;
                int calories;
                System.out.println("Serch vagetable ");
                System.out.println("Enter calories ");
                Scanner scan = new Scanner(System.in);
                calories = Integer.parseInt(scan.next());

                for(Vegetables vegetables:sald)
                    {
                        if (vegetables.getCalories() == calories)
                            {
                               vagetable = vegetables.getVegetables();
                                break;
                            }

                    }
                if (vagetable!=null)
                    {
                        System.out.println("your vagetable is " + vagetable);
                    }
                else {
                    System.out.println("don't found your vagetable");
                }
            }
        public void allVegetablesWeight()
            {
                int saldWeight = 0;
                for (Vegetables vegetables: sald)
                    {
                        saldWeight += vegetables.getWeight();
                    }
                System.out.println("Sald's  weight = " +saldWeight);

            }


        public class MyComparator  implements Comparator<Vegetables>

        {


            @Override
            public int compare (Vegetables o1, Vegetables o2)
            {
                if (o1.getCalories() < o2.getCalories())
                    {
                        return -1;
                    }
                if (o1.getCalories() > o2.getCalories())
                    {
                        return 1;
                    }
                return 0;
            }

        }
    }
