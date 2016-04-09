package collectionNuber;

import listInterface.MyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class NuberDateMyList
    {
        MyList date = new MyList();


        public void addNumber(double number)
            {
                date.add(number);
            }

        public void removeNuber(double number)
            {
                date.remove(number);
            }

        public void sercNumber()
            {
                System.out.println("Enter your nuber");
                Scanner scan = new Scanner(System.in);
                double number = Double.parseDouble(scan.next());
                Iterator iterator = date.iterator();
                Double min = (Double) iterator.next();

                while (iterator.hasNext()) {
                    Double elem = (Double) iterator.next();
                    if (Math.abs(elem - number) < Math.abs(min - number)) {
                        min = elem;
                    }
                }

                System.out.println("Found nuber " +  min);

            }

        public void showDate()
            {
                System.out.print("[ ");
                for (Object nubers : date)
                    {
                        System.out.print(nubers + " ");
                    }
                System.out.println("]");

            }
    }
