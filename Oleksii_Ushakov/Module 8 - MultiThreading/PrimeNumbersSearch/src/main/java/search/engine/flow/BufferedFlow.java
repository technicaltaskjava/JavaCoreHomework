package search.engine.flow;

import interval.Interval;
import number.PrimeNumber;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class BufferedFlow implements Runnable {
    protected Interval interval;
    private List<Integer> buffer = new LinkedList<>();

    public BufferedFlow(Interval interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        for (int i = interval.getLeftBorder(); i <= interval.getRightBorder(); i++) {
            if (PrimeNumber.isPrimeNumber(i)) {
                buffer.add(i);
            }
        }
    }

    public List<Integer> getBuffer() {
        return buffer;
    }

    public Interval getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return String.format("Flow %s;", interval);
    }
}


