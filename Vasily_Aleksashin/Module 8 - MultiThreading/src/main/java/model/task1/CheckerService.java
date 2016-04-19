package model.task1;

import exception.ParameterIncorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class CheckerService {
	private static final Logger logger = LoggerFactory.getLogger(CheckerService.class);

	private final List<Integer> primes = Collections.synchronizedList(new ArrayList<Integer>());
	private final int lowerBound;
	private final int upperBound;
	private final int threadCount;

	public CheckerService(final int lowerBound, final int upperBound, final int threadCount) throws ParameterIncorrectException {
		if (threadCount < 1) {
			throw new ParameterIncorrectException("Threads count can not be less then 1");
		}
		this.lowerBound = Math.min(lowerBound, upperBound);
		this.upperBound = Math.max(lowerBound, upperBound);
		this.threadCount = threadCount;
	}

	public void findPrimeRunnable() throws ParameterIncorrectException {
		final List<Checker> threads = preparedThreadList(primes);
		for (Checker element : threads) {
			final Thread thread = new Thread(element);
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
		}
	}

	public void findPrimeCallable() throws ParameterIncorrectException {
		final List<Checker> threads = preparedThreadList();
		final List<FutureTask<List<Integer>>> taskList = getListFutureTask(threads);
		ExecutorService executor = Executors.newFixedThreadPool(taskList.size());
		for (FutureTask<List<Integer>> task : taskList) {
			executor.execute(task);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		for (FutureTask<List<Integer>> task : taskList) {
			try {
				primes.addAll(task.get());

			} catch (InterruptedException | ExecutionException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public List<Integer> getPrimes() {
		return primes.isEmpty() ? Collections.<Integer>emptyList() : primes;
	}

	private List<FutureTask<List<Integer>>> getListFutureTask(final List<Checker> threads) throws ParameterIncorrectException {
		Validator.isNull(threads);
		List<FutureTask<List<Integer>>> list = new ArrayList<>();
		for (Checker thread : threads) {
			list.add(new FutureTask<>(thread));
		}
		return list;
	}

	private List<Checker> preparedThreadList() throws ParameterIncorrectException {
		return preparedThreadList(null);
	}

	private List<Checker> preparedThreadList(final List<Integer> primes) throws ParameterIncorrectException {
		final List<Checker> threads = new ArrayList<>();
		final int step = getStep();
		int start = lowerBound;
		int end = start + step;
		for (int index = 0; index <= threadCount; index++) {
			if (start > upperBound) {
				break;
			}
			if (end > upperBound) {
				end = upperBound;
			}
			if (primes == null) {
				threads.add(new Checker(start, end));
			} else {
				threads.add(new Checker(start, end, primes));
			}
			start = end + 1;
			end = start + step;
		}
		return threads;
	}

	private int getStep() {
		final int count = upperBound - lowerBound + 1;
		return count / threadCount;
	}
}
