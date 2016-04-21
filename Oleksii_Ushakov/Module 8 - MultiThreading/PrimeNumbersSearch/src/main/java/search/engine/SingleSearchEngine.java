package search.engine;

import interval.Interval;
import org.apache.log4j.Logger;
import search.NumberSearchEngine;
import search.engine.flow.Flow;

import java.util.concurrent.*;

/**
 * @author Alexey Ushakov
 */
public class SingleSearchEngine implements NumberSearchEngine {
    private static final Logger logger = Logger.getLogger("console");
    private final Interval interval;
    private final int threadCount;
    private BlockingQueue<Integer> buffer;
    private Flow[] flows;
    private int size = 0;

    public SingleSearchEngine(Interval interval, int threadCount) {
        this.interval = interval;
        this.threadCount = threadCount;
        buffer = new LinkedBlockingQueue<>();

        flows = new Flow[threadCount];
        Interval[] intervals = interval.getEqualIntervals(threadCount);

        for (int i = 0; i < threadCount; i++) {
            flows[i] = new Flow(intervals[i], buffer);
        }
    }

    @Override
    public void start() throws InterruptedException {
        try {

            ExecutorService service = Executors.newFixedThreadPool(threadCount);

            for (Flow flow : flows) {
                service.submit(flow);
            }

            service.shutdown();
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            logger.info(e);
            throw e;
        } finally {
            size = buffer.size();
        }
    }

    @Override
    public int getSearchPrimesCount() {
        return size;
    }

    @Override
    public String getName() {
        return "Single engine";
    }

    @Override
    public Interval getInterval() {
        return interval;
    }
}
