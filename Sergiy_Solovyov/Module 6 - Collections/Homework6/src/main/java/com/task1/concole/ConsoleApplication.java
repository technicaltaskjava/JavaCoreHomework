package com.task1.concole;


import com.task1.transport.airplanecomp.AirCompany;
import com.task1.transport.airplanecomp.traingeneration.trainfromfile.ReadFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Sergey Solovyov
 */
public class ConsoleApplication {

    private AirCompany company;
    private ReadFromFile readFromFile = new ReadFromFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleApplication.class);

    public ConsoleApplication(){
        readFromFile.readPlaneFromFile();
        company = readFromFile.getAirCompany();


    }
    public void run(){
        System.out.println("Welcome to transport company \"Super AirLines\"\n");
        printMenu();
        while (menu())
            printMenu();
        System.out.println("Bye-bye, we hope to see you later");
    }

    private void printMenu(){
        System.out.println("1 - see information about planes in our company");
        System.out.println("2 - sort planes by range of flight and see information");
        System.out.println("3 - calculate total weight of luggage");
        System.out.println("4 - calculate how many passengers all planes may accommodate");
        System.out.println("5 - find planes by colour");
        System.out.println("6 - exit the program");
        System.out.println("Enter the number and press enter");
    }

    private boolean menu(){
        boolean goOn = true;
        switch (consoleMenuInput()){
            case 1: System.out.println(company.toString());
                    break;
            case 2: company.sortByFlightRange();
                    System.out.println(company.toString());
                    break;
            case 3: company.planeLuggageWeight();
                    break;
            case 4: company.planePassengersCapacity();
                    break;
            case 5:
                    System.out.println(company.findPlaneByColour(consoleColourInput()));
                    break;
            case 6: goOn = false;
                break;
            default:break;
        }
        return goOn;
    }

    private   int consoleMenuInput(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;
        String str = "";

       do {
             try {
                   str = bufferedReader.readLine();
                   menu = Integer.parseInt(str);
                   if (menu < 1 || menu > 6){
                       System.out.println("***********Please, enter the number from menu***********");}

               }

             catch (IOException|NumberFormatException e) {
                   System.out.println("***********Please, enter only numbers without blanks**********");
                   LOGGER.info(e.getMessage(), e);
               }
        } while (str.isEmpty());

        return menu;
    }
    private  String  consoleColourInput(){

        System.out.println("Enter one of next colours:" +
                           " red, white, blue.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        do {
            try {
                str = bufferedReader.readLine();
                if (str.isEmpty()) {
                    break;
                }

            } catch (IOException e) {
                LOGGER.info(e.getMessage(), e);
            }
        }while (str.isEmpty());
        return str;
    }
}
