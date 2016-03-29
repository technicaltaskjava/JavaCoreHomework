import structures.*;
import utils.ComparatorSortExample;

import java.util.Comparator;

import static utils.CompareUtils.*;


public class Main {
    public static void main(String[] args){

        System.out.println("\nTask 1.");
        TestedClass.run();


        System.out.println("\nTask 2.");
        //Using a type that implements Comparable.
        Integer[] arrInt = new Integer[10];
        System.out.println("Values in randomized integer array: ");
        for (int i = 0; i < arrInt.length; i++){
            arrInt[i] = (int)(Math.random()*1000);
            System.out.print(arrInt[i] + " ");
        }
        System.out.print("\nMin = " + getMin(arrInt));
        System.out.print(", max = " + getMax(arrInt));
        System.out.println(", median = " + getMedian(arrInt));
        //Using a comparator with a type that doesn't implement Comparable.
        ComparatorSortExample[] arrObject = new ComparatorSortExample[10];
        System.out.println("Values in randomized object array: ");
        for (int i = 0; i < arrObject.length; i++){
            arrObject[i] = new ComparatorSortExample((int)(Math.random()*1000), i);
            System.out.print(arrObject[i].id + ":" + arrObject[i].value + "\t");
        }
        Comparator comp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int thisValue = Integer.parseInt(o1.toString());
                int nextValue = Integer.parseInt(o2.toString());
                return Integer.compare(thisValue, nextValue);
            }
        };
        ComparatorSortExample result = getMin(arrObject, comp);
        System.out.print("\nMin = " + result.id + ":" + result.value);
        result = getMax(arrObject, comp);
        System.out.print(", max = " + result.id + ":" + result.value);
        result = getMedian(arrObject, comp);
        System.out.println(", median = " + result.id + ":" + result.value);


        System.out.println("\nTask 3.");
        Unit testUnit = Tuples.create("This is a unit.");
        System.out.println(testUnit.getOne());
        Pair testPair = Tuples.create("This is a pair.", 30.06);
        System.out.println(testPair.getOne() + " " + testPair.getTwo());
        Triplet testTriplet = Tuples.create("This is a triplet.", 0.357, "Bang-bang.");
        System.out.println(testTriplet.getOne() + " " + testTriplet.getTwo() + " " + testTriplet.getThree());
    }
}
