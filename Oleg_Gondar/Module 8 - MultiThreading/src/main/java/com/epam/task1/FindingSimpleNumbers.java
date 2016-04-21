package com.epam.task1;

import java.util.Set;


/**
 * Created by Oleg on 17.04.2016.
 */
public class FindingSimpleNumbers implements Runnable {

    private int startNumber;
    private int endNumber;
    private Set<Integer> simpleNumbers;

    public FindingSimpleNumbers(int startNumber, int endNumber, Set<Integer> simpleNumbers) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.simpleNumbers = simpleNumbers;
    }

    @Override
    public void run() {
        findSimpleNumbers();
    }

    static boolean isSimple(int numberToVerify) {
        for (int i = 2; i < numberToVerify; i++) {
            if (numberToVerify % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void findSimpleNumbers() {

        for (int i = startNumber; i <= endNumber; i++) {
            if (isSimple(i)) {
                synchronized (simpleNumbers) {
                    simpleNumbers.add(i);
                }
            }
        }
    }
}
