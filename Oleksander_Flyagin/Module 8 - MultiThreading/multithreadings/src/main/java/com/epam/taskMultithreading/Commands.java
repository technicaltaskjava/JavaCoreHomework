package com.epam.taskMultithreading;


import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class Commands {
    private Logger myLog = Logger.getLogger("Loger");
    private NuberSerchTread myTreadGetList;
    private NuberSercherTread myTreadGetNubers;
    private int tread;
    private int start;
    private int end;
    private List nubers = new CopyOnWriteArrayList();
    private long startTime, finish;


    private void setDiaposon() {
        System.out.println("Enter begin nuber");
        Scanner startN = new Scanner(System.in);
        start = startN.nextInt();
        System.out.println("Enter end nuber");
        Scanner endN = new Scanner(System.in);
        end = endN.nextInt();
        System.out.println("Enter tread nubers");
        Scanner treadN = new Scanner(System.in);
        tread = treadN.nextInt();
        nubers = new CopyOnWriteArrayList();
        startTime = System.currentTimeMillis();
    }


    private int strtPO() {
        int res;
        int treads;

        res = end - start;
        if (res <= tread) {
            treads = 1;
        } else {
            treads = res / tread;
        }
        return treads;
    }


    public void writeListsToList() {
        setDiaposon();
        int stap = 0;
        int treads = strtPO();
        while (stap < tread) {

            int last;
            last = start + treads;

            if (last > end) {
                last = end + 1;
            }
            myTreadGetNubers = new NuberSercherTread();
            myTreadGetNubers.setBegin(start);
            myTreadGetNubers.setLast(last);
            myTreadGetNubers.start();
            stap++;
            start += treads;
            try {
                myTreadGetNubers.join();
                nubers.addAll(myTreadGetNubers.getNuberList());
            } catch (InterruptedException e) {
                myLog.warning("WORNING" + e);
                Thread.currentThread().interrupt();

            }

        }
        chekLive(myTreadGetNubers);
    }

    public void writeInOneList() {
        setDiaposon();
        int stap = 0;
        int treads = strtPO();

        while (stap < tread) {
            int last;
            last = start + treads;

            if (last > end) {
                last = end + 1;
            }
            myTreadGetList = new NuberSerchTread(start, last, nubers);
            stap++;
            start += treads;
        }

        chekLive(myTreadGetList);
    }

    private void chekLive(Thread thread) {
        try {
            thread.join();
            System.out.println(nubers);
            finish = System.currentTimeMillis();
            System.out.println("Time = " + (finish - startTime));
        } catch (InterruptedException e) {
            myLog.warning("WORNING" + e);
            Thread.currentThread().interrupt();
        }


    }


}



