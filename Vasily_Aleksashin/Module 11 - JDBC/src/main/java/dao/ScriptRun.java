package dao;

import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScriptRun implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ScriptRun.class);
	private final BlockingQueue<String> scripts;
	private AtomicInteger operationCount;
	private DaoFactory factory;
	private boolean isQueueEmpty;


	public ScriptRun(final BlockingQueue<String> scripts, final AtomicInteger operationCount) throws DaoException {
		this.scripts = scripts;
		this.operationCount = operationCount;
		factory = DaoFactoryImpl.getInstance();
	}

	@Override
	public void run() {
		while (!isQueueEmpty) {
			if (!scripts.isEmpty()) {
				final String query = scripts.poll();
				try (Connection connection = factory.getConnection();
				     PreparedStatement statement = connection.prepareStatement(query)) {
					operationCount.addAndGet(statement.executeUpdate());
					logger.info(String.format("added from %s", Thread.currentThread().getName()));
				} catch (SQLException | DaoException e) {
					logger.error(String.format("Cannot execute query '%s'", query), e);
				}
			} else {
				isQueueEmpty = true;
			}
		}
	}
}
