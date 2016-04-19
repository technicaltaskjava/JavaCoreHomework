package search;

import interval.Interval;

/**
 * @author Alexey Ushakov
 */
public interface NumberSearchEngine {
    void start() throws InterruptedException;

    Interval getInterval();

    int getSearchPrimesCount();

    String getName();
}
