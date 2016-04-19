package com.task1.thread;

import static com.task1.utility.PrimeChecker.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;


/**
 * @author Sergey Solovyov
 */
public class PrimeFinder implements Runnable, Callable<List<Integer>> {

    private int start;
    private int end;
    private ArrayList<Integer> threadList = new ArrayList<>();
    private static  List<Integer> primes = Collections.synchronizedList(new ArrayList<Integer>());

    public PrimeFinder(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {

          for (int i = start; i <= end; i++){
              if (isPrime(i))
                  primes.add(i);
        }
    }

    @Override
    public List<Integer> call() throws Exception {
        for (int i = start; i <= end; i++){
            if (isPrime(i))
                threadList.add(i);
        }
        if (threadList.isEmpty())
            return Collections.emptyList();
        else return threadList;
    }

    public List<Integer> getThreadList() {
        return threadList;
    }

    public static List<Integer> getPrimes() {
        return primes;
    }
}
