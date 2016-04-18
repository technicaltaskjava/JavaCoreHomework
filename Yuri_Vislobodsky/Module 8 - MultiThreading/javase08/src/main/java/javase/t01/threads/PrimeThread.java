package javase.t01.threads;

import javase.t01.handler.PrimeHandlerMethod;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to get prime numbers in thread
 * Created by Yury Vislobodsky on 13.04.2016.
 */
public class PrimeThread implements Runnable {
    private Set<Integer> localSet = new TreeSet<>();
    private final Set<Integer> commonSet;
    private PrimeHandlerMethod method;
    private Thread thread;
    private int minValue;
    private int maxValue;

    public PrimeThread(int minValue, int maxValue,
                       Set<Integer> commonSet, PrimeHandlerMethod method) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.commonSet = commonSet;
        this.method = method;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        boolean isMethod2 = method == PrimeHandlerMethod.METHOD2;
        for (int number = minValue; number <= maxValue; number++) {
            if (isPrimeNumber(number)) {
                if (isMethod2) {
                    localSet.add(number);
                } else {
                    addNumberToCommonSet(number);
                }
            }
        }
        if (isMethod2) {
            addLocalSetToCommonSet();
        }
    }

    private void addNumberToCommonSet(int number) {
        synchronized (commonSet) {
            commonSet.add(number);
        }
    }

    private void addLocalSetToCommonSet() {
        synchronized (commonSet) {
            commonSet.addAll(localSet);
        }
    }

    public Thread getThread() {
        return thread;
    }

    private boolean isPrimeNumber(int numberValue) {
        for (int number = 2; number <= (numberValue / 2); number++) {
            if (numberValue % number == 0) {
                return false;
            }
        }
        return true;
    }
}
