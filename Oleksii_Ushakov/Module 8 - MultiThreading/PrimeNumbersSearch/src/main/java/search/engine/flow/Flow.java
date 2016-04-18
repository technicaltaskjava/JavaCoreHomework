package search.engine.flow;

import interval.Interval;
import number.PrimeNumber;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Alexey Ushakov
 */
public class Flow extends AbstractFlow {
    private ConcurrentLinkedQueue<Integer> buffer;

    public Flow(Interval interval, ConcurrentLinkedQueue<Integer> buffer) {
        super(interval);
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
}
