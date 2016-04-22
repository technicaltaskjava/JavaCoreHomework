package primes;

import java.util.Collection;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Primes {

    static final String RUNS = " runs: ";
    static final String MS = " ms";
    static TreeSet<Integer> results;
    static ConcurrentSkipListSet<Integer> resultsConcurrent;
    static Thread[] primeFinderThreads;

    private Primes(){}

    public static void start(int times, int start, int end, int threads){

        long startTime = System.currentTimeMillis();
        long bufferTimes = 0;
        long syncTimes = 0;
        long concurrentTimes = 0;
        System.out.println("Running all methods " + times + " times. Please wait.");
        try {
            for (int i = 0; i < times; i++) {
                bufferTimes = bufferTimes + Primes.buffers(start, end, threads);
                syncTimes = syncTimes + Primes.sync(start, end, threads);
                concurrentTimes = concurrentTimes + Primes.concurrent(start, end, threads);
            }
        } catch (InterruptedException e){
            System.out.println("Something went terribly wrong." + e);
            Thread.currentThread().interrupt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + MS);
        System.out.println("Execution time for buffer over " + times + RUNS + bufferTimes + MS);
        System.out.println("Execution time for sync over " + times + RUNS + syncTimes + MS);
        System.out.println("Execution time for concurrent over " + times + RUNS + concurrentTimes + MS);
        System.out.println("Average execution time for buffer: " + (double)bufferTimes/times + MS);
        System.out.println("Average execution time for sync: " + (double)syncTimes/times + MS);
        System.out.println("Average execution time for concurrent: " + (double)concurrentTimes/times + MS);
    }

    public static Collection<Integer> getResults(){
        return results;
    }

    public static synchronized void write(int input){
        results.add(input);
    }

    public static long buffers(int start, int end, int threads) throws InterruptedException {
        results = new TreeSet<>();
        long startTime = System.currentTimeMillis();
        int threadSize = (end - start) / threads;
        int remainder = (end - start) % threads;
        PrimeFinderWithBuffer[] primeFinders = new PrimeFinderWithBuffer[threads];
        primeFinderThreads = new Thread[threads];
        for (int i = 0; i < threads - 1; i++){
            primeFinders[i] = new PrimeFinderWithBuffer(start + i*threadSize + 1, start + (i+1)*threadSize);
            primeFinderThreads[i] = new Thread(primeFinders[i]);
            primeFinderThreads[i].start();
        }
        primeFinders[threads-1] = new PrimeFinderWithBuffer(end - (threadSize + remainder) + 1, end);
        primeFinderThreads[threads-1] = new Thread(primeFinders[threads-1]);
        primeFinderThreads[threads-1].start();
        for (int i = 0; i < threads; i++){
            primeFinderThreads[i].join();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long sync(int start, int end, int threads) throws InterruptedException {
        results = new TreeSet<>();
        long startTime = System.currentTimeMillis();
        int threadSize = (end - start) / threads;
        int remainder = (end - start) % threads;
        PrimeFinder[] primeFinders = new PrimeFinder[threads];
        primeFinderThreads = new Thread[threads];
        for (int i = 0; i < threads - 1; i++){
            primeFinders[i] = new PrimeFinder(start + i*threadSize + 1, start + (i+1)*threadSize);
            primeFinderThreads[i] = new Thread(primeFinders[i]);
            primeFinderThreads[i].start();
        }
        primeFinders[threads-1] = new PrimeFinder(end - (threadSize + remainder) + 1, end);
        primeFinderThreads[threads-1] = new Thread(primeFinders[threads-1]);
        primeFinderThreads[threads-1].start();
        for (int i = 0; i < threads; i++){
            primeFinderThreads[i].join();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long concurrent(int start, int end, int threads) throws InterruptedException {
        resultsConcurrent = new ConcurrentSkipListSet<>();
        long startTime = System.currentTimeMillis();
        int threadSize = (end - start) / threads;
        int remainder = (end - start) % threads;
        PrimeFinderWithConcurrent[] primeFinders = new PrimeFinderWithConcurrent[threads];
        primeFinderThreads = new Thread[threads];
        for (int i = 0; i < threads - 1; i++){
            primeFinders[i] = new PrimeFinderWithConcurrent(start + i*threadSize + 1, start + (i+1)*threadSize);
            primeFinderThreads[i] = new Thread(primeFinders[i]);
            primeFinderThreads[i].start();
        }
        primeFinders[threads-1] = new PrimeFinderWithConcurrent(end - (threadSize + remainder) + 1, end);
        primeFinderThreads[threads-1] = new Thread(primeFinders[threads-1]);
        primeFinderThreads[threads-1].start();
        for (int i = 0; i < threads; i++){
            primeFinderThreads[i].join();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
