package search.engine;

import interval.Interval;
import org.apache.log4j.Logger;
import search.engine.flow.Flow;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexey Ushakov
 */
public class SingleSearchEngine extends AbstractSearchEngine {
    private final Logger logger = Logger.getLogger("console");
    private ConcurrentLinkedQueue<Integer> buffer;
    private Flow[] flows;

    public SingleSearchEngine(Interval interval, int threadCount) {
        super(interval, threadCount);
        buffer = new ConcurrentLinkedQueue<>();

        flows = new Flow[threadCount];
        Interval[] intervals = interval.getEqualIntervals(threadCount);

        for (int i = 0; i < threadCount; i++) {
            flows[i] = new Flow(intervals[i], buffer);
        }
    }

    @Override
    public void start() {
        Flow currentFlow = null;
        try {

            ExecutorService service = Executors.newFixedThreadPool(threadCount);

            for (Flow flow : flows) {
                currentFlow = flow;
                service.submit(flow);
            }

            service.shutdown();
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            logger.info(currentFlow, e.getCause());
        }
    }

    @Override
    public int getSearchPrimesCount() {
        return buffer.size();
    }

    @Override
    public String getName() {
        return "Single engine";
    }
}
