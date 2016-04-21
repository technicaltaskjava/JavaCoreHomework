package com.epam.task1;

import java.util.TreeSet;

/**
 * Created by o.gondar on 20.04.2016.
 */
public class FindWithDifferentCollections {
    private FindWithDifferentCollections() {
    }

    public static void findWithDifferentCollections(RangeMaker ranges, ResulstDTO results) throws InterruptedException {

        TreeSet<Integer> allSimpleNumbers = new TreeSet();
        TreeSet<Integer> simpleNumbers1 = new TreeSet();
        TreeSet<Integer> simpleNumbers2 = new TreeSet();
        TreeSet<Integer> simpleNumbers3 = new TreeSet();
        TreeSet<Integer> simpleNumbers4 = new TreeSet();

        Thread find1 = new Thread(new FindingSimpleNumbers(ranges.getStartFirstRange(), ranges.getEndFirstRange(), simpleNumbers1));
        Thread find2 = new Thread(new FindingSimpleNumbers(ranges.getStartSecondRange(), ranges.getEndSecondRange(), simpleNumbers2));
        Thread find3 = new Thread(new FindingSimpleNumbers(ranges.getStartThirdRange(), ranges.getEndThirdRange(), simpleNumbers3));
        Thread find4 = new Thread(new FindingSimpleNumbers(ranges.getStartFourthRange(), ranges.getEndFourthRange(), simpleNumbers4));

        long startTime = System.nanoTime();
        find1.start();
        find2.start();
        find3.start();
        find4.start();
        find1.join();
        find2.join();
        find3.join();
        find4.join();

        allSimpleNumbers.addAll(simpleNumbers1);
        allSimpleNumbers.addAll(simpleNumbers2);
        allSimpleNumbers.addAll(simpleNumbers3);
        allSimpleNumbers.addAll(simpleNumbers4);

        long endTime = System.nanoTime();
        results.setRuntime(endTime - startTime);
        results.setSimpleNumbers(allSimpleNumbers);

    }
}
