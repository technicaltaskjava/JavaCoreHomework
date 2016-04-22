package search.engine.flow;

import interval.Interval;

/**
 * @author Alexey Ushakov
 */
public abstract class AbstractFlow implements Runnable {
    protected Interval interval;

    public AbstractFlow(Interval interval) {
        this.interval = interval;
    }

    public Interval getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return String.format("Flow %s;", interval);
    }
}
