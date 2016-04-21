package com.epam.task1;

import java.util.Scanner;
import java.util.Set;

/**
 * Created by o.gondar on 21.04.2016.
 */
public class WorkWithConsole {
    private WorkWithConsole() {
    }

    public static void printResults(Set<Integer> simpleNumbers) {

        System.out.println("Simple numbers:");
        for (Integer i :
                simpleNumbers) {
            System.out.println(i);
        }
    }

    public static RangeMaker getUserInput(){
        Scanner scanner = new Scanner(System.in);
        int startNumber;
        int endNumber;
        do {
            System.out.println("Enter positive numbers.\nLast number bigger than first!");
            startNumber = scanner.nextInt();
            endNumber = scanner.nextInt();
        } while (startNumber <= 0 || endNumber <= 0 || startNumber >= endNumber);
        return new RangeMaker(startNumber, endNumber);
    }
}
