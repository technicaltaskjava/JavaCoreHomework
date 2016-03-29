package ua.valentin.annotations;


import java.util.Scanner;
/**
 * Class to launch the calculator . It supports user input .
 * Created by yakimenko.valentin on 29.03.16.
 */
public class InteractRunner {
    public static void main(String[] arg) {
        try (Scanner reader = new Scanner(System.in)) {
            Calculator calc = new Calculator();
            String exit = "no";
            while (!exit.equals("yes")) {
                System.out.println("Enter first arg : ");
                String first = reader.next();
                System.out.println("Enter second arg : ");
                String second = reader.next();
                try {
                    calc.div(Integer.valueOf(first), Integer.valueOf(second));
                } catch (UserException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter two args.");
                }

                System.out.println("Result : " + calc.getResult());
                calc.cleanResult();
                System.out.println("Exit : yes/no ");
                exit = reader.next();
            }
        }
    }
 }
