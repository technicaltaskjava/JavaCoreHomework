package search.engine.flow;

import interval.Interval;
import number.PrimeNumber;

import java.util.Collection;

/**
 * @author Alexey Ushakov
 */
public class Flow implements Runnable {
    protected Interval interval;
    private Collection<Integer> buffer;

    public Flow(Interval interval, Collection<Integer> buffer) {
        this.interval = interval;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = interval.getLeftBorder(); i <= interval.getRightBorder(); i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                buffer.add(i);
            }
        }
    }

    public Interval getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return String.format("Flow %s;", interval);
    }
}
