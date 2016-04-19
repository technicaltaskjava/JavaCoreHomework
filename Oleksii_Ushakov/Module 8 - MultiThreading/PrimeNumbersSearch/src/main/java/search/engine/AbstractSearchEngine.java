package search.engine;

import interval.Interval;
import search.NumberSearchEngine;

/**
 * @author Alexey Ushakov
 */
public abstract class AbstractSearchEngine implements NumberSearchEngine {
    protected final Interval interval;
    protected final int threadCount;

    public AbstractSearchEngine(Interval interval, int threadCount) {
        this.interval = interval;
        this.threadCount = threadCount;
    }

    @Override
    public Interval getInterval() {
        return interval;
    }
}
