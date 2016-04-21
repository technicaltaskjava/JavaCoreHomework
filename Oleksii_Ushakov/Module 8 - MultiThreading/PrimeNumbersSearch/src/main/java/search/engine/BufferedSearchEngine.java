package search.engine;

import interval.Interval;
import org.apache.log4j.Logger;
import search.NumberSearchEngine;
import search.engine.flow.BufferedFlow;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexey Ushakov
 */
public class BufferedSearchEngine implements NumberSearchEngine {
    private static final Logger logger = Logger.getLogger("console");
    private final Interval interval;
    private final int threadCount;

    private List<Integer> buffer = new LinkedList<>();
    private BufferedFlow[] flows;

    public BufferedSearchEngine(Interval interval, int threadCount) {
        this.interval = interval;
        this.threadCount = threadCount;

        flows = new BufferedFlow[threadCount];
        Interval[] intervals = interval.getEqualIntervals(threadCount);

        for (int i = 0; i < threadCount; i++) {
            flows[i] = new BufferedFlow(intervals[i]);
        }

    }

    @Override
    public void start() throws InterruptedException {
        BufferedFlow currentFlow = null;
        try {

            ExecutorService service = Executors.newFixedThreadPool(threadCount);

            for (BufferedFlow flow : flows) {
                currentFlow = flow;
                service.submit(flow);
            }

            service.shutdown();
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            logger.info(currentFlow, e);
            throw e;
        } finally {
            for (BufferedFlow flow : flows) {
                buffer.addAll(flow.getBuffer());
            }
        }
    }

    @Override
    public String getName() {
        return "Buffered search";
    }

    @Override
    public int getSearchPrimesCount() {
        return buffer.size();
    }

    @Override
    public Interval getInterval() {
        return interval;
    }
}
