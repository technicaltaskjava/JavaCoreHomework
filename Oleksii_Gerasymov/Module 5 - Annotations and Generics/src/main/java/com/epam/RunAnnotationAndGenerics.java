package com.epam;

import com.epam.t01.ReflectionTestMain;
import com.epam.t02.CompareUtilsMain;
import com.epam.t03.TupleClassMain;

import java.util.Scanner;

public class RunAnnotationAndGenerics {
    public static Scanner userInput;

    public static void main(String args[]) {
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] Annotations and reflections testing.");
            System.out.println("[2] Generics. Comparing array implementation (example on test data).");
            System.out.println("[3] Generics. Tuples implementation (example on test data).");
            System.out.println("[4] Exit program.");

            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1: {
                        ReflectionTestMain.mainMenu();
                        break;
                    }
                    case 2: {
                        CompareUtilsMain.mainMenu();
                        break;
                    }
                    case 3: {
                        TupleClassMain.mainMenu();
                        break;
                    }
                    case 4: {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Incorrect number. You must input one digit from 1 to 4.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 4.\n");
            }
        }
    }
}
