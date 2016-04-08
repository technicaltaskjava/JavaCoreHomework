package com.epam.task.three;

public class NumberSetRun {

    private NumberSetRun() {
    }

    public static void mainMenu() {
        NumberSet testSet = new NumberSet();
        testSet.addElement(24.1);
        testSet.addElement(-14.2);
        testSet.addElement(12.6);
        testSet.addElement(-8.3);
        testSet.addElement(6.24);
        testSet.addElement(54.78);
        testSet.addElement(133.7);
        printSet(testSet);

        testSet.removeElement(54.78);
        System.out.println("Value 54.78 removed from set.");
        printSet(testSet);

        System.out.println("Nearby value of -2.5 is : " + testSet.seekNear(-2.5));
    }

    private static void printSet(NumberSet testSet) {
        System.out.println("Size of Set is : " + testSet.getLength());
        System.out.println("Current list is : " + testSet.getSet());
        System.out.println();
    }
}
