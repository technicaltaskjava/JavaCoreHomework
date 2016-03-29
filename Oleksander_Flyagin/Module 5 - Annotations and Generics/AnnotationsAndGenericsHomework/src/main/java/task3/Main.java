package task3;

import task3.elements.Pair;
import task3.elements.Triplet;
import task3.elements.Unit;

public class Main

    {
        public static void main(String[] args)
            {

                Tuples tuples =new Tuples();
                tuples.add(Pair.creat("pair", 1));
                tuples.add(Triplet.creat(Pair.creat("pair",2), "TEST", 3));
                tuples.add(Triplet.creat(Pair.creat("pair",2), "TEST", 4));
                tuples.add(Unit.creat("Unit"));
                for (Object tuple:tuples)
                    {
                        System.out.println(tuple);
                    }





            }


    }
