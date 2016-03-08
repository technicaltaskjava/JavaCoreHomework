package com.epam.javase02.mobileTariffs.tariffMaker;

import com.epam.javase02.mobileTariffs.allTariffs.MobileTariff;
import com.epam.javase02.mobileTariffs.allTariffs.contractTariffs.BusinessTariff;
import com.epam.javase02.mobileTariffs.allTariffs.contractTariffs.IndividualTariff;
import com.epam.javase02.mobileTariffs.allTariffs.prepaidTariffs.PrepaidTariff;
import com.epam.javase02.mobileTariffs.allTariffs.randomTariffs.RandomBusinessTariff;
import com.epam.javase02.mobileTariffs.allTariffs.randomTariffs.RandomIndividualTariff;
import com.epam.javase02.mobileTariffs.allTariffs.randomTariffs.RandomPrepaidTariff;
import com.epam.javase02.mobileTariffs.comparators.*;
import com.epam.javase02.mobileTariffs.printer.PrintResult;

import java.util.Arrays;
import java.util.Scanner;

public class TariffsMaker {
    private MobileTariff[] tariffsList = new MobileTariff[0];
    PrintResult printer = new PrintResult();
    public void createRandomTariffs() {
        String[] typesAccount = {"prepaid", "individual", "business"};
        int numberOfRandomTariffs = 5;
        int count = 0;
        while (count < numberOfRandomTariffs) {
            String accountType = typesAccount[(int) (Math.random() * typesAccount.length)];
            String randomTariffName = "Random " + (int) (Math.random() * 100);
            switch (accountType) {
                case "prepaid":
                    RandomPrepaidTariff randomPrepaidTariff = new RandomPrepaidTariff(randomTariffName, accountType);
                    randomPrepaidTariff.setParameters();
                    MobileTariff[] temp1 = tariffsList.clone();
                    tariffsList = new MobileTariff[temp1.length + 1];
                    tariffsList[temp1.length] = randomPrepaidTariff;
                    for (int i = 0; i < temp1.length; i++) {
                        tariffsList[i] = temp1[i];
                    }
                    break;
                case "individual":
                    RandomIndividualTariff randomIndividualTariff = new RandomIndividualTariff(randomTariffName, accountType);
                    randomIndividualTariff.setParameters();
                    MobileTariff[] temp2 = tariffsList.clone();
                    tariffsList = new MobileTariff[temp2.length + 1];
                    tariffsList[temp2.length] = randomIndividualTariff;
                    for (int i = 0; i < temp2.length; i++) {
                        tariffsList[i] = temp2[i];
                    }
                    break;
                case "business":
                    RandomBusinessTariff randomBusinessTariff = new RandomBusinessTariff(randomTariffName, accountType);
                    randomBusinessTariff.setParameters();
                    MobileTariff[] temp3 = tariffsList.clone();
                    tariffsList = new MobileTariff[temp3.length + 1];
                    tariffsList[temp3.length] = randomBusinessTariff;
                    for (int i = 0; i < temp3.length; i++) {
                        tariffsList[i]  = temp3[i];
                    }
                    break;
            }
            count++;
        }
    }
    public void viewTariff() {
        printer.printListTariff(tariffsList);
    }
    public void createNewTariff(String accountType) {
        int minutesNumber;
        int megabytesNumber;
        int messagesNumber;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the tariff's name:");
        String tariffName = scan.nextLine();
        System.out.print("Set (per month):\nnumber of minute - \n");
        minutesNumber = scan.nextInt();
        System.out.print("number of megabytes - \n");
        megabytesNumber = scan.nextInt();
        System.out.print("number of SMS and MMS - \n");
        messagesNumber = scan.nextInt();
        switch (accountType) {
            case "prepaid":
                PrepaidTariff prepaidTariff = new PrepaidTariff(tariffName, accountType);
                prepaidTariff.setMinutesPerMonth(minutesNumber);
                prepaidTariff.setMegabytesPerMonth(megabytesNumber);
                prepaidTariff.setSmsAndMmsPerMonth(messagesNumber);
                prepaidTariff.setCustomerAmount(1);
                printer.printUserTariff(prepaidTariff);
                MobileTariff[] temp1 = tariffsList.clone();
                tariffsList = new MobileTariff[temp1.length + 1];
                tariffsList[temp1.length] = prepaidTariff;
                for (int i = 0; i < temp1.length; i++) {
                    tariffsList[i] = temp1[i];
                }
                break;
            case "individual":
                IndividualTariff individualTariff = new IndividualTariff(tariffName, accountType);
                individualTariff.setMinutesPerMonth(minutesNumber);
                individualTariff.setMegabytesPerMonth(megabytesNumber);
                individualTariff.setSmsAndMmsPerMonth(messagesNumber);
                individualTariff.setCustomerAmount(1);
                printer.printUserTariff(individualTariff);
                MobileTariff[] temp2 = tariffsList.clone();
                tariffsList = new MobileTariff[temp2.length + 1];
                tariffsList[temp2.length] = individualTariff;
                for (int i = 0; i < temp2.length; i++) {
                    tariffsList[i] = temp2[i];
                }
                break;
            case "business":
                BusinessTariff businessTariff = new BusinessTariff(tariffName,accountType);
                businessTariff.setMinutesPerMonth(minutesNumber);
                businessTariff.setMegabytesPerMonth(megabytesNumber);
                businessTariff.setSmsAndMmsPerMonth(messagesNumber);
                businessTariff.setCustomerAmount(1);
                printer.printUserTariff(businessTariff);
                MobileTariff[] temp3 = tariffsList.clone();
                tariffsList = new MobileTariff[temp3.length + 1];
                tariffsList[temp3.length] = businessTariff;
                for (int i = 0; i < temp3.length; i++) {
                    tariffsList[i] = temp3[i];
                }
                break;
            default:
                System.out.println("Wrong choice!");
                break;
        }
    }

    public void searchByMinutes(int minimum, int maximum) {
        int tariffIndex;
        MobileTariff[] requiredTariffs = new MobileTariff[0];
        String requiredTariffName = null;
        for (tariffIndex = 0; tariffIndex < tariffsList.length; tariffIndex++) {
            int minutesPerMonth = tariffsList[tariffIndex].getMinutesPerMonth();
            if (minutesPerMonth >= minimum && minutesPerMonth <= maximum) {
                requiredTariffName = tariffsList[tariffIndex].getTariffName();
                MobileTariff[] temp = requiredTariffs.clone();
                requiredTariffs = new MobileTariff[temp.length + 1];
                requiredTariffs[temp.length] = tariffsList[tariffIndex];
                for (int i = 0; i < temp.length; i++) {
                    requiredTariffs[i]  = temp[i];
                }
            }
        }
        printer.printListTariff(requiredTariffs);
        if (requiredTariffName == null) {
            System.out.println("There is not required tariff.");
        }
    }
    public void searchByCostPerMonth(int minimum, int maximum) {
        int tariffIndex;
        int requiredTariffCost = 0;
        MobileTariff[] requiredTariffs = new MobileTariff[0];
        for (tariffIndex = 0; tariffIndex < tariffsList.length; tariffIndex++) {
            int costPerMonth = tariffsList[tariffIndex].getCostPerMonth();
            if (costPerMonth >= minimum && costPerMonth <= maximum) {
                requiredTariffCost = tariffsList[tariffIndex].getCostPerMonth();
                MobileTariff[] temp = requiredTariffs.clone();
                requiredTariffs = new MobileTariff[temp.length + 1];
                requiredTariffs[temp.length] = tariffsList[tariffIndex];
                for (int i = 0; i < temp.length; i++) {
                    requiredTariffs[i]  = temp[i];
                }
            }
        }
        printer.printListTariff(requiredTariffs);
        if (requiredTariffCost == 0) {
            System.out.println("There is not required tariff.");
        }
    }

    public void sortByMinutesPerMonth() {
        Arrays.sort(tariffsList, new MinutesComparator());
        System.out.println("Sorted tariffs by minutes per month:");
        printer.printListTariff(tariffsList);
    }
    public void sortByCostPerMonth() {
        Arrays.sort(tariffsList, new CostComparator());
        System.out.println("Sorted tariffs by cost per month:");
        printer.printListTariff(tariffsList);
    }
    public void sortByMegabytesPerMonth() {
        Arrays.sort(tariffsList, new MegabytesComparator());
        System.out.println("Sorted tariffs by megabytes per month:");
        printer.printListTariff(tariffsList);
    }
    public void sortBySmsAndMmsPerMonth() {
        Arrays.sort(tariffsList, new SmsAndMmsComparator());
        System.out.println("Sorted tariffs by SMS and MMS per month:");
        printer.printListTariff(tariffsList);
    }
    public void sortByCustomersAmount() {
        Arrays.sort(tariffsList, new CustomerComparator());
        System.out.println("Sorted tariffs by customers amount:");
        printer.printListTariff(tariffsList);
    }
    public void countCustomers() {
        int totalCustomersAmount = 0;
        for (int i = 0; i < tariffsList.length; i++) {
            totalCustomersAmount += tariffsList[i].getCustomerAmount();
        }
        System.out.println("Total customers amount is " + totalCustomersAmount + ".\n");
    }
}
