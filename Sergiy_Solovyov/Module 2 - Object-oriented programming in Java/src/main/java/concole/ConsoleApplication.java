package concole;

import transport.train.Train;
import transport.train.traingeneration.randomgeneration.TrainGenerator;
import transport.train.traingeneration.trainfromfile.ReadFromFile;

import java.io.*;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class ConsoleApplication {

    private Train train;
    private ReadFromFile readFromFile = new ReadFromFile();

    public ConsoleApplication(){
        train = TrainGenerator.generateTrain(3, 3, 3);    //you may also generate train with reading from file


    }
    public void run(){
        System.out.println("Welcome to transport company \"SuperTrains\"" +
                          ", you have unique possibility to know more about our first train!\n");
        printMenu();
        while (menu())printMenu();
        System.out.println("Bye-bye, we hope to see you later");
    }

    private void printMenu(){
        System.out.println("1 - see information about the train");
        System.out.println("2 - sort carriages by age and see information");
        System.out.println("3 - calculate total weight of luggage");
        System.out.println("4 - calculate how many passengers train may accommodate");
        System.out.println("5 - find carriages by colour");
        System.out.println("6 - exit the program");
        System.out.println("Enter the number and press enter");
    }

    private boolean menu(){
        boolean goOn = true;
        switch (consoleMenuInput()){
            case 1: System.out.println(train.toString());
                    break;
            case 2: train.sortByAge();
                    System.out.println(train.toString());
                    break;
            case 3: train.trainLuggageWeight();
                    break;
            case 4: train.trainPassengersCapacity();
                    break;
            case 5:
                    System.out.println(train.findCarriagesByColour(consoleColourInput()));
                    break;
            case 6: goOn = false;
                break;
        }
        return goOn;
    }

    private   int consoleMenuInput(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;
        String str = null;

       do {
             try {
                   str = bufferedReader.readLine();
                   menu = Integer.parseInt(str);
                   if (menu < 1 || menu > 6){
                       System.out.println("***********Please, enter the number from menu***********");}

               } catch (Exception e) {
                   System.out.println("***********Please, enter only numbers without blanks**********");
               }
        } while (str.isEmpty());

        return menu;
    }
    private  String  consoleColourInput(){

        System.out.println("Enter one of next colours:" +
                           " red, white, blue.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        do {
            try {
                str = bufferedReader.readLine();
                if (str.isEmpty()) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (str.isEmpty());
        return str;
    }
}
