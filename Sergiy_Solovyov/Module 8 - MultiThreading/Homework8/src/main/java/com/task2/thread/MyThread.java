package com.task2.thread;

import com.task2.entity.Transaction;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Sergey Solovyov
 */
public class MyThread implements Runnable {

    public static final AtomicInteger COUNTER = new AtomicInteger(0);
    private Queue<Transaction> queue;

    public MyThread(Queue<Transaction> queue){
        this.queue = queue;
    }
    @Override
    public void run() {

        while (!queue.isEmpty()){

           Transaction transaction =  queue.poll();
            if (transaction == null)
                break;

            transaction.runTransaction();
            System.out.println(transaction + " - " + "Thread name: "
                    + Thread.currentThread().getName() + " - Trans №: " + COUNTER.incrementAndGet());

        }
    }

}
