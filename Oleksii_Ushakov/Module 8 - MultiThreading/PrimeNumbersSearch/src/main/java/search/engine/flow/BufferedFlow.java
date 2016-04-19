package search.engine.flow;

import interval.Interval;
import number.PrimeNumber;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class BufferedFlow extends AbstractFlow {
    private List<Integer> buffer = new LinkedList<>();

    public BufferedFlow(Interval interval) {
        super(interval);
    }

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
}


