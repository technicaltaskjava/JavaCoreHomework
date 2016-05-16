package dao.pool;

import exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomConnectionPool {
	private static final Logger logger = LoggerFactory.getLogger(CustomConnectionPool.class);
	private static final int DEFAULT_CONNECTION_COUNT = 5;

	private final BlockingQueue<PooledConnection> availableConnections;
	private final BlockingQueue<PooledConnection> usedConnections;
	private final String url;
	private final String user;
	private final String pass;
	private boolean isDispose;

	private CustomConnectionPool(int connectionCount, final String url, final String user, final String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		availableConnections = new LinkedBlockingQueue<>();
		usedConnections = new LinkedBlockingQueue<>();
		int availableConnectionCount = connectionCount < 1 ? DEFAULT_CONNECTION_COUNT : connectionCount;
		addConnection(availableConnectionCount);
	}

	public static CustomConnectionPool create(final String url, final String user, final String pass) {
		return new CustomConnectionPool(0, url, user, pass);
	}

	public static CustomConnectionPool create(int connectionCount, final String url, final String user, final String pass) {
		return new CustomConnectionPool(connectionCount, url, user, pass);
	}

	public synchronized Connection getConnection() throws DaoException {
		if (isDispose) {
			throw new DaoException("Connection pool is dispose");
		}
		if (availableConnections.isEmpty()) {
			throw new DaoException("Available connections are absent");
		}
		final PooledConnection connection = availableConnections.poll();
		usedConnections.offer(connection);
		return connection;
	}

	public int getAvailableConnections() {
		return availableConnections.size();
	}

	public int getActiveConnections() {
		return usedConnections.size();
	}

	public void dispose() {
		if (isDispose) {
			return;
		}
		for (PooledConnection connection : usedConnections) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		removeConnection();
		isDispose = true;
	}

	synchronized void returnConnection(final PooledConnection connection) {
		Validator.isNull(connection);
		if (usedConnections.contains(connection)) {
			usedConnections.remove(connection);
			availableConnections.offer(connection);
		}
	}

	private void addConnection(final int addCount) {
		for (int index = 0; index < addCount; index++) {
			final Connection connection;
			try {
				connection = DriverManager.getConnection(url, user, pass);
				availableConnections.add(new PooledConnection(connection, this));
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void removeConnection() {
		for (int index = 0; index < availableConnections.size(); index++) {
			PooledConnection connection = availableConnections.poll();
			try {
				connection.closeConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
