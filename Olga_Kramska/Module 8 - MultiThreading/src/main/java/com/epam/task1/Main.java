package com.epam.task1;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Olga Kramska on 16-Apr-16.
 */
public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number starting with \"2\" (start of search interval): ");
        int start = in.nextInt();

        System.out.print("Enter the number starting with \"2\" (end of search interval): ");
        int end = in.nextInt();

        System.out.print("Enter the number of threads to use: ");
        int threadsNumber = in.nextInt();

        long startMethod1 = System.currentTimeMillis();

        Set<Integer> set1 = FindingLauncher.findSimpleNumbers(start, end, threadsNumber, new TreeSet<Integer>());

        long endMethod1 = System.currentTimeMillis();

        System.out.println("\n" + set1);
        System.out.println("Working time with common collection " + (endMethod1 - startMethod1) + " milliseconds");

        long startMethod2 = System.currentTimeMillis();

        Set<Integer> set2 = FindingLauncher.findSimpleNumbers(start, end, threadsNumber);

        long endMethod2 = System.currentTimeMillis();

        System.out.println("\n" +set2);
        System.out.println("Working time with separate collections " + (endMethod2 - startMethod2) + " milliseconds");

    }
}
