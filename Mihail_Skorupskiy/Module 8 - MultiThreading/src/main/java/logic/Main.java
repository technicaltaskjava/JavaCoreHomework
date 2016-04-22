package logic;

import primes.*;
import transfers.AccountManager;
import transfers.RunnableTransfer;
import transfers.TransferManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int CORES = Runtime.getRuntime().availableProcessors();

    private Main(){}

    public static void main(String[] args) {
        int input = -1;
        do {
            System.out.println("Choose your destiny:");
            System.out.println("1 - task one.");
            System.out.println("2 - task two.");
            System.out.println("0 - exit.");
            try {
                input = integerInput();
            } catch (IOException e) {
                System.out.println("Wrong input." + e);
            }
            switch(input){
                case 0:
                    break;
                case 1:
                    taskOne();
                    break;
                case 2:
                    taskTwo();
                    break;
                default:
            }
        } while (input != 0);


    }

    static void taskOne(){
        System.out.println("Task 1.");
        try {
            System.out.println("Enter number of iterations");
            int iterations = integerInput();
            System.out.println("Enter start of number range");
            int start = integerInput();
            System.out.println("Enter end of number range");
            int end = integerInput();
            System.out.println("Enter number of threads");
            int threads = integerInput();
            Primes.start(iterations, start, end, threads);
            System.out.println("Results: ");
            System.out.println(Primes.getResults());
        } catch (IOException e){
            System.out.println("Wrong input. " + e);
        }
    }

    static void taskTwo(){
        System.out.println("Task 2.");
        AccountManager.initialize();
        System.out.println("\nAccounts before: ");
        AccountManager.show();
        System.out.println();
        //Transfers are read from src/main/resources/transfers.txt in the constructor call below.
        TransferManager manager = new TransferManager();
        ExecutorService executor = Executors.newFixedThreadPool(CORES);
        for (int i = 0; i < CORES; i++){
            executor.execute(new RunnableTransfer(manager));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("\nAccounts after: ");
        AccountManager.show();
        System.out.println("\nTotal delayed transfers: " + manager.getDelayedSize());
    }

    static int integerInput() throws IOException{
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e){
            throw new IOException(e);
        }
    }

}
