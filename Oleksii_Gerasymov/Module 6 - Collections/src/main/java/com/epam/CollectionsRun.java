package com.epam;

import com.epam.task.four.ParkingRun;
import com.epam.task.one.airline.AirlineRun;
import com.epam.task.three.NumberSetRun;
import com.epam.task.two.MyListRun;

import java.io.FileNotFoundException;
import java.util.Scanner;

class CollectionsRun {
    static Scanner userInput;

    private CollectionsRun() {
    }

    public static void main(String[] args) {
        userInput = new Scanner(System.in);

        boolean makeTask = true;
        while (makeTask) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] OOP in Java with Collections (Task 2.8: Airline).");
            System.out.println("[2] Data structures. Array-based implementation of List.");
            System.out.println("[3] Data structures. Number keeper.");
            System.out.println("[4] Data structures. Parking.");
            System.out.println("[5] Exit program.");
            System.out.println("Fifth task is in xls-file according to lectors explanation.");
            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1:
                        AirlineRun.mainMenu();
                        break;
                    case 2:
                        MyListRun.mainMenu();
                        break;
                    case 3:
                        NumberSetRun.mainMenu();
                        break;
                    case 4:
                        ParkingRun.mainMenu();
                        break;
                    case 5:
                        makeTask = false;
                        break;
                    default:
                        System.out.println("Incorrect number. You must input one digit from 1 to 5.\n");
                        break;
                }
            }
            catch (NumberFormatException | FileNotFoundException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 5.\n");
            }
        }
    }
}
