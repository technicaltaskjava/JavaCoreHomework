package search.engine.flow;

import interval.Interval;
import number.PrimeNumber;

import java.util.Collection;

/**
 * @author Alexey Ushakov
 */
public class Flow extends AbstractFlow {
    private Collection<Integer> buffer;

    public Flow(Interval interval, Collection<Integer> buffer) {
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
