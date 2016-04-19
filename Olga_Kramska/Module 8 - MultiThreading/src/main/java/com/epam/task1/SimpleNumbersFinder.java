package com.epam.task1;

import java.util.Set;

/**
 * Created by Olga Kramska on 16-Apr-16.
 */
public class SimpleNumbersFinder implements Runnable {
    private Set<Integer> results;
    private int start;
    private int end;

    public SimpleNumbersFinder(int start, int end, Set<Integer> results) {
        this.start = start;
        this.end = end;
        this.results = results;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            int k = 0;
            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    k++;
                }
            }
            if (k == 0) {
                results.add(i);
            }
        }
    }
}
