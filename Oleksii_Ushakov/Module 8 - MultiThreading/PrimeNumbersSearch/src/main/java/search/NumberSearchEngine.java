package search;

import interval.Interval;

/**
 * @author Alexey Ushakov
 */
public interface NumberSearchEngine {
    void start();

    Interval getInterval();

    int getThreadCount();

    int getSearchPrimesCount();

    String getName();
}
