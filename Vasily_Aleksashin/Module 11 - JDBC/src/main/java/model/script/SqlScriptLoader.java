package model.script;

import dao.ScriptRun;
import exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.SqlScriptParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlScriptLoader {
	private static final Logger logger = LoggerFactory.getLogger(SqlScriptLoader.class);

	private final int processorCount;
	private final AtomicInteger operationCount = new AtomicInteger(0);
	private BlockingQueue<String> scripts = new LinkedBlockingQueue<>();

	public SqlScriptLoader() {
		processorCount = Runtime.getRuntime().availableProcessors();
	}

	void erase() {
		scripts = new LinkedBlockingQueue<>();
	}

	public void getScript(final String scriptFileName) {
		try {
			scripts = SqlScriptParser.parse(scriptFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public int load() throws DaoException {
		operationCount.set(0);
		List<Thread> threads = new ArrayList<>();
		for (int index = 0; index < processorCount; index++) {
			threads.add(new Thread(new ScriptRun(scripts, operationCount)));
		}
		if (scripts.isEmpty()) {
			return -1;
		}
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
				thread.interrupt();
			}
		}
		return operationCount.get();
	}
}
