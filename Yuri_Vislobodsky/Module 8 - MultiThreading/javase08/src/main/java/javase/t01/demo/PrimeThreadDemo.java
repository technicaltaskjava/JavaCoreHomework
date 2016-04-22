package javase.t01.demo;

import javase.t01.handler.PrimeHandler;
import javase.t01.handler.PrimeHandlerMethod;
import java.util.Scanner;

/**
 * Class to demonstrate results of prime numbers calculation
 * by two given methods
 * Created by Yury Vislobodsky on 13.04.2016.
 */
public class PrimeThreadDemo {
    private static int minValue;
    private static int maxValue;
    private static int threadsCount;

    private PrimeThreadDemo() {}

    public static boolean inputParameters() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a minimum value : ");
        minValue = scanner.nextInt();

        if (minValue <= 0) {
            System.out.println("Min value must be greater than zero!");
            return false;
        }
        System.out.print("Input a maximum value : ");
        maxValue = scanner.nextInt();
        if (minValue > maxValue) {
            System.out.println("Max value must be greater or equal to min value!");
            return false;
        }
        System.out.print("Input a number of threads : ");
        threadsCount = scanner.nextInt();
        if (threadsCount <= 0) {
            System.out.println("Number of threads must be greater than zero!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        if (inputParameters()) {
            PrimeHandler primeHandler = new PrimeHandler(minValue, maxValue, threadsCount);

            primeHandler.execute(PrimeHandlerMethod.METHOD1);
            System.out.println(primeHandler);

            primeHandler.execute(PrimeHandlerMethod.METHOD2);
            System.out.println(primeHandler);
        }
    }
}
