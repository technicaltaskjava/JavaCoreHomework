package javase05.t03.demo;

import javase05.t03.tuples.*;

/**
 * Demo class for tuples
 * Created by Yury Vislobodsky on 27.03.2016.
 */
public class TuplesDemo {
    public static void main(String[] args) {
        Pair<String,Triplet<Integer,Character,Double>> pair =
                Tuples.create("Hello", Tuples.create(1,'A',3.0));
        System.out.println(pair);

        // for example, access to second element in Pair
        // and then to third element in Triplet
        System.out.println(pair.getB().getC());
    }
}
