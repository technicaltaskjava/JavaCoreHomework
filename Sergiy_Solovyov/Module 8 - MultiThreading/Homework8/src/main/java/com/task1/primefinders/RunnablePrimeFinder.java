package com.task1.primefinders;

import com.task1.utility.RangeDivider;
import com.task1.thread.PrimeFinder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Solovyov
 */
public class RunnablePrimeFinder {

    public  void run(int start, int end, int threads) throws InterruptedException {

        List<Integer> list = RangeDivider.divide(start, end, threads);

        System.out.print(list);
        System.out.println(" - ranges");

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < list.size()-1; i+=2){
            executorService.execute(new PrimeFinder(list.get(i),list.get(i+1)));
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

    }


}
