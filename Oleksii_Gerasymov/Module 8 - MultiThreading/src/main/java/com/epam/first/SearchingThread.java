package com.epam.first;

import java.util.SortedSet;

public class SearchingThread extends Thread {
    private int startOfRange;
    private int endOfRange;
    private SortedSet<Integer> simpleNumberSet;

    public SearchingThread(Integer startOfRange, Integer endOfRange, SortedSet<Integer> simpleNumberSet) {
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
        this.simpleNumberSet = simpleNumberSet;
    }

    @Override
    public void run() {
        for (int numberCounter = startOfRange; numberCounter <= endOfRange; numberCounter++) {
            boolean isSimple = true;
            for (int testCounter = 2; testCounter <= (numberCounter / 2); testCounter++) {
                if (numberCounter % testCounter == 0) {
                    isSimple = false;
                    break;
                }
            }
            if (isSimple) {
                simpleNumberSet.add(numberCounter);
            }
        }
    }
}
