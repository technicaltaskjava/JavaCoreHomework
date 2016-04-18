package javase.t01.handler;

import javase.t01.threads.PrimeThread;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * Handler Class for running threads and adding prime numbers to common set
 * Created by Yury Vislobodsky on 13.04.2016.
 */
public class PrimeHandler {
    private Set<Integer> commonSet = new TreeSet<>();
    private int minValue;
    private int maxValue;
    private int threadsCount;
    private int step;
    private long totalTime;
    private PrimeHandlerMethod method;

    public PrimeHandler(int minValue, int maxValue, int threadsCount) {
        this.minValue = Math.max(minValue, 0);
        this.maxValue = Math.max(maxValue, this.minValue);
        this.threadsCount = Math.max(threadsCount, 1);
        step = (int) Math.ceil((maxValue - minValue + 1) / (double) threadsCount);
    }

    public void execute(PrimeHandlerMethod method) throws InterruptedException {
        this.method = method;
        commonSet.clear();
        long startTime = System.currentTimeMillis();

        PrimeThread[] primeThreads = new PrimeThread[threadsCount];
        for (int index = 0; index < primeThreads.length; index++) {
            int firstValue = minValue + step * index;
            int lastValue = Math.min(firstValue + step - 1, maxValue);
            primeThreads[index] = new PrimeThread(firstValue, lastValue, commonSet, method);
        }

        for (PrimeThread primeThread : primeThreads) {
            primeThread.getThread().join();
        }

        totalTime = System.currentTimeMillis() - startTime;
    }

    public int getSize() {
        return commonSet.size();
    }

    @Override
    public String toString() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis( totalTime );
        SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        return method + "\nPrime numbers set: " + commonSet.toString() +
                "\nSize: " + commonSet.size() + ", duration: " + format.format(cal.getTime()) + "\n";
    }
}
