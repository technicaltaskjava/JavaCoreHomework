package com.epam.javase02.mobileTariffs.userSpeaker;

import com.epam.javase02.mobileTariffs.tariffMaker.TariffsMaker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserSpeaker {
    public void chooseOptions() {
        TariffsMaker tariffsList = new TariffsMaker();
        tariffsList.createRandomTariffs();
        Scanner scanner = new Scanner(System.in);
        int userChoice = -1;

        while (userChoice != 0) {
            System.out.println("MENU");
            System.out.println("1 - view existing tariffs");
            System.out.println("2 - create your own tariff");
            System.out.println("3 - search tariff by criteria");
            System.out.println("4 - sort tariff by criteria");
            System.out.println("5 - view total existing customers amount");
            System.out.println("0 - exit");
            try {
                userChoice = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Wrong input!");
                scanner.next();
                userChoice = -1;
            }
            switch (userChoice) {
                case 1:
                    tariffsList.viewTariff();
                    break;
                case 2:
                    System.out.println("Choose account type:");
                    System.out.println(" 1 - prepaid\n 2 - individual contract\n 3 - business contract");
                    System.out.println("You created tariff:");
                    int typeChoice = scanner.nextInt();
                    if (typeChoice == 1) {
                        tariffsList.createNewTariff("prepaid");
                    }else if (typeChoice == 2) {
                        tariffsList.createNewTariff("individual");
                    } else if (typeChoice == 3) {
                        tariffsList.createNewTariff("business");
                    } else {
                        System.out.println("Wrong input!");
                    }
                    break;
                case 3:
                    System.out.println("Please choose search criteria:");
                    System.out.println(" 1 - by minutes\n 2 - by cost per month");
                    int searchCriteria = scanner.nextInt();
                    if (searchCriteria == 1) {
                        System.out.println("Enter minimum:");
                        int minimum = scanner.nextInt();
                        System.out.println("Enter maximum:");
                        int maximum = scanner.nextInt();
                        tariffsList.searchByMinutes(minimum, maximum);
                    } else if (searchCriteria == 2) {
                        System.out.println("Enter minimum:");
                        int minimum = scanner.nextInt();
                        System.out.println("Enter maximum:");
                        int maximum = scanner.nextInt();
                        tariffsList.searchByCostPerMonth(minimum, maximum);
                    } else {
                        System.out.println("Wrong input!");
                    }
                    break;
                case 4:
                    System.out.println("Please choose sort criteria:");
                    System.out.println(" 1 - by cost per month\n 2 - by minutes\n 3 - by megabytes\n 4 - by SMS and MMS\n 5 - by customers amount");
                    int sortCriteria = scanner.nextInt();
                    if (sortCriteria == 1) {
                        tariffsList.sortByCostPerMonth();
                    } else if (sortCriteria == 2) {
                        tariffsList.sortByMinutesPerMonth();
                    } else if (sortCriteria == 3) {
                        tariffsList.sortByMegabytesPerMonth();
                    } else if (sortCriteria == 4) {
                        tariffsList.sortBySmsAndMmsPerMonth();
                    } else if (sortCriteria == 5) {
                        tariffsList.sortByCustomersAmount();
                    } else {
                        System.out.println("Wrong input!");
                    }
                    break;
                case 5:
                    tariffsList.countCustomers();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
        scanner.close();
    }
}
