package com.epam.taskMultithreading;


import java.util.List;


public class NuberSerchTread extends Thread {

    private int start;
    private int end;
    private List nubersov;


    public NuberSerchTread(int start, int end, List nubersov) {

        this.start = start;
        this.end = end;
        this.start();
        this.nubersov = nubersov;
    }

    @Override
    public void run() {
        int divisor = 0;
        for (int i = start; i < end; i++) {
            for (long j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divisor++;
                }
                if (divisor > 2) {
                    divisor = 0;
                }
            }
            if (divisor == 2) {
                nubersov.add(i);

            }
            divisor = 0;
        }


    }


}






