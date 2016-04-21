package com.epam.task1;

import java.util.*;


/**
 * Created by Oleg on 16.04.2016.
 */
public class Run {
    private Run() {
    }

    public static void main(String[] args) throws InterruptedException {

        RangeMaker range = WorkWithConsole.getUserInput();

        int manyCollectionsWinsCount = 0;
        int oneCollectionWinsCount = 0;
        ResulstDTO singleCollectionResults = new ResulstDTO();
        ResulstDTO manyCollectionResults = new ResulstDTO();

        for (int i = 0; i < 100; i++) {
            FindWithOneCollection.findWithOneCollection(range, singleCollectionResults);
            FindWithDifferentCollections.findWithDifferentCollections(range, manyCollectionResults);
            long temp = singleCollectionResults.getRuntime() - manyCollectionResults.getRuntime();
            if (temp < 0) {
                oneCollectionWinsCount++;
            } else if (temp > 0) {
                manyCollectionsWinsCount++;
            }
        }
        System.out.println("Results for finding with different collections for threads:");
        WorkWithConsole.printResults(manyCollectionResults.getSimpleNumbers());
        System.out.println("Results for finding with one common collection for threads:");
        WorkWithConsole.printResults(singleCollectionResults.getSimpleNumbers());
        System.out.println("____________________________________________");
        System.out.println((manyCollectionsWinsCount - oneCollectionWinsCount > 0
                ? "Finding with many collections faster after 100 runs "
                : "Finding with one collection faster after 100 runs ")
                + "\nScore: " + oneCollectionWinsCount + ":" + manyCollectionsWinsCount);
    }

}
