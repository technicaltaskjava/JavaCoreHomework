package com.epam;

import com.epam.abstractfactory.AbstractFactoryRun;
import com.epam.builder.BuilderRun;
import com.epam.singleton.SingletonRun;
import com.epam.strategy.StrategyRun;

import java.util.Scanner;
import java.util.logging.Logger;

public class DesignPatternsRun {
    static Scanner userInput;
    private static Logger log = Logger.getLogger(DesignPatternsRun.class.getName());

    private DesignPatternsRun() {
    }

    public static void main(String[] args) {
        userInput = new Scanner(System.in);
        boolean makeTask = true;

        while (makeTask) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] Design Patterns. Singleton Example.");
            System.out.println("[2] Design Patterns. Abstract Factory Example.");
            System.out.println("[3] Design Patterns. Builder Example.");
            System.out.println("[4] Design Patterns. Strategy Example.");
            System.out.println("[5] Exit program.");
            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1:
                        SingletonRun.currencyExchangeExample();
                        break;
                    case 2:
                        AbstractFactoryRun.equipmentSetRentExample();
                        break;
                    case 3:
                        BuilderRun.cocktailMakerExample();
                        break;
                    case 4:
                        StrategyRun.gunsExample();
                        break;
                    case 5:
                        makeTask = false;
                        break;
                    default:
                        System.out.println("Incorrect number. You must input one digit from 1 to 5.\n");
                        break;
                }
            } catch (NumberFormatException userException) {
                log.info(String.valueOf(userException));
                System.out.println("Incorrect input.\n");
            }
        }
    }
}
