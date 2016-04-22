package com.task1.primefinders;

import com.task1.utility.RangeDivider;
import com.task1.thread.PrimeFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author Sergey Solovyov
 */
public class CallablePrimeFinder {

    private List<Integer> primes = Collections.synchronizedList(new ArrayList<Integer>());
    private static final Logger LOGGER = LoggerFactory.getLogger(CallablePrimeFinder.class);

    public void run(int start, int end, int threads) throws InterruptedException {

      List<Integer> list = RangeDivider.divide(start, end, threads);

        System.out.print(list);
        System.out.println(" - ranges");
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<FutureTask<List<Integer>>>tasks = new ArrayList<>();

        for (int i = 0; i < list.size()-1; i+=2){

         PrimeFinder thread= new PrimeFinder(list.get(i),list.get(i+1));
            FutureTask<List<Integer>>task = new FutureTask<>(thread);
            tasks.add(task);
            executorService.execute(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        for (FutureTask<List<Integer>> task : tasks){
            try {
                primes.addAll(task.get());
            } catch (ExecutionException e) {
                LOGGER.info(e.getClass().toString(), e);
            }
        }
    }

    public List<Integer> getPrimes() {
        return primes;
    }
}
