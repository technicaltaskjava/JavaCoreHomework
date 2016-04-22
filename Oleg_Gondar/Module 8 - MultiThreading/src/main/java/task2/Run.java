package task2;

import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Oleg on 20.04.2016.
 */
public class Run {



    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Account account1 = new Account(300);
        Account account2 = new Account(300);

        FileProcessSinchronized fileProcessSinchronized1 = new FileProcessSinchronized(account1,account2,"1.txt", 1, 2);
        FileProcessSinchronized fileProcessSinchronized2 = new FileProcessSinchronized(account1,account2,"1.txt", 3, 4);

        FileProcessConcurrent fileProcessConcurrent1 = new FileProcessConcurrent(account1,account2,"1.txt", 1, 2);
        FileProcessConcurrent fileProcessConcurrent2 = new FileProcessConcurrent(account1,account2,"1.txt", 3, 4);

        run(fileProcessSinchronized1, fileProcessSinchronized2);
        account1.showAll();
        account2.showAll();

        run(new Thread(fileProcessConcurrent1),new Thread(fileProcessConcurrent2));


            account1.showAll();
            account2.showAll();


    }

    public static void run(Thread t1, Thread t2) throws InterruptedException {
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
