package com.epam;

import com.epam.first.FindSimpleNumbers;
import com.epam.second.AccountsOperation;

import java.util.Scanner;
import java.util.logging.Logger;

public class MultiThreadingRun {
    static Scanner userInput;
    private static Logger log = Logger.getLogger(MultiThreadingRun.class.getName());
    private static final String ACCOUNTS_LOG = "src/main/resources/cashflow.txt";
    private static final String BEGINNING_BALANCE = "src/main/resources/balance.txt";

    private MultiThreadingRun() {
    }

    public static void main(String[] args) {
        userInput = new Scanner(System.in);

        boolean makeTask = true;
        while (makeTask) {
            System.out.println("Enter the number of task:");
            System.out.println("[1] Multithreading. Find simply numbers.");
            System.out.println("[2] Synchronization. Accounts operation with synchronized keyword.");
            System.out.println("[3] Synchronization. Accounts operation with concurrent collection.");
            System.out.println("[4] Exit program.");
            String userLine = userInput.nextLine();

            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {
                    case 1:
                        FindSimpleNumbers.mainMenu(userInput);
                        break;
                    case 2:
                        AccountsOperation.mainMenu(ACCOUNTS_LOG, BEGINNING_BALANCE, false);
                        break;
                    case 3:
                        AccountsOperation.mainMenu(ACCOUNTS_LOG, BEGINNING_BALANCE, true);
                        break;
                    case 4:
                        makeTask = false;
                        break;
                    default:
                        System.out.println("Incorrect number. You must input one digit from 1 to 4.\n");
                        break;
                }
            }
            catch (NumberFormatException userException) {
                log.info(String.valueOf(userException));
                System.out.println("Incorrect input.\n");
            }
        }
    }
}
