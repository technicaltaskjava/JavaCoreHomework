package com.epam.task1;

import java.util.TreeSet;

/**
 * Created by o.gondar on 20.04.2016.
 */
public class FindWithOneCollection {
    private FindWithOneCollection() {
    }

    public static void findWithOneCollection(RangeMaker ranges, ResulstDTO resulst) throws InterruptedException {

        TreeSet<Integer> simpleNumbers = new TreeSet();

        Thread find1 = new Thread(new FindingSimpleNumbers(ranges.getStartFirstRange(), ranges.getEndFirstRange(), simpleNumbers));
        Thread find2 = new Thread(new FindingSimpleNumbers(ranges.getStartSecondRange(), ranges.getEndSecondRange(), simpleNumbers));
        Thread find3 = new Thread(new FindingSimpleNumbers(ranges.getStartThirdRange(), ranges.getEndThirdRange(), simpleNumbers));
        Thread find4 = new Thread(new FindingSimpleNumbers(ranges.getStartFourthRange(), ranges.getEndFourthRange(), simpleNumbers));

        long startTime = System.nanoTime();
        find1.start();
        find2.start();
        find3.start();
        find4.start();
        find1.join();
        find2.join();
        find3.join();
        find4.join();

        long endTime = System.nanoTime();
        resulst.setRuntime(endTime - startTime);
        resulst.setSimpleNumbers(simpleNumbers);
    }
}
