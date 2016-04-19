package com.task1.application;

import com.console.Console;
import com.google.common.base.Stopwatch;
import com.task1.exceptions.WrongArgumentException;
import com.task1.primefinders.CallablePrimeFinder;
import com.task1.primefinders.RunnablePrimeFinder;
import com.task1.thread.PrimeFinder;
import com.task1.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey Solovyov
 */
public class Application {

    private int start;
    private int end;
    private int threads;
    private Console console = new Console();
    private CallablePrimeFinder callableFinder = new CallablePrimeFinder();
    private RunnablePrimeFinder runnableFinder = new RunnablePrimeFinder();
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public void run() throws InterruptedException {
        boolean flag = true;

        while (flag){
            System.out.println("Hello!\n");
            System.out.println("Enter start number of the range(min - 1)");
            start = console.intInput();
            System.out.println("Enter end number of the range(min - 10)");
            end = console.intInput();
            System.out.println("Enter thread quantity");
            threads = console.intInput();
            try {
                Validator.validate(start, end, threads);
            } catch (WrongArgumentException e) {
                LOGGER.info(e.getClass().toString(), e);
                continue;
            }
            flag = false;
        }

            Stopwatch stopwatch = Stopwatch.createStarted();
            System.out.println("Solution with one collection starts working");
            runnableFinder.run(start, end, threads);
            System.out.println(PrimeFinder.getPrimes());
            System.out.println("Execution time: " + stopwatch.stop() + "\n");
            System.out.println("Solution with collection in each thread starts working");
            Stopwatch stopwatch2 = Stopwatch.createStarted();
            callableFinder.run(start, end, threads);
            System.out.println(callableFinder.getPrimes());
            System.out.println("Execution time: " + stopwatch2.stop() + "\n");

    }
}
