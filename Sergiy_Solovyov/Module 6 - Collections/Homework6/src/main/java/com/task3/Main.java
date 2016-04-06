package com.task3;



/**
 * @author Sergey Solovyov
 */
public class Main {
    private Main(){};
    public static void main(String[] args) {
        NumbersHolder<Integer> myStructure = new NumbersHolder<>();
        System.out.println("NumbersHolder created: " + myStructure.toString() );
        myStructure.addNumber(1);
        myStructure.addNumber(9);
        myStructure.addNumber(13);
        myStructure.addNumber(2);
        myStructure.addNumber(5);
        myStructure.addNumber(7);
        myStructure.addNumber(111);
        System.out.println("After adding numbers - " + myStructure.toString());
        myStructure.printClosestNumber(Integer.MIN_VALUE);
        myStructure.printClosestNumber(5);
        myStructure.printClosestNumber(14);
        myStructure.printClosestNumber(100);
        myStructure.printClosestNumber(Integer.MAX_VALUE);
        myStructure.removeByIndex(2);
        System.out.println("After removing by index(2) - " + myStructure.toString());



    }

}
