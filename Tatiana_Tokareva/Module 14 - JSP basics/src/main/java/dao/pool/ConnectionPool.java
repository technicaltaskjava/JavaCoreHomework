package dao.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	
	private BlockingQueue<PooledConnection> available;
	private BlockingQueue<PooledConnection> used;
	 final String url;
	 final String user;
	 final String password;
	private static final int CONNECTION_SIZE = 8;
	private Connection connection;

	private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
	
	public ConnectionPool(final String url, final String user, final String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		
		
		try {
			Class.forName("org.h2.Driver");
			used = new ArrayBlockingQueue<>(CONNECTION_SIZE);
			available = new ArrayBlockingQueue<>(CONNECTION_SIZE);
			for (int index = 0; index < CONNECTION_SIZE; index++) {
				connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection, this);
				available.add(pooledConnection);

			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public Connection getConnection() throws InterruptedException {
		PooledConnection pooledConnection = null;
		if (available != null) {
			pooledConnection = available.take();
			used.add(pooledConnection);
		}

		return pooledConnection;
		
	}
	

	public void giveBackConnection(PooledConnection connection) {

		if (connection != null & used.contains(connection)) {

				used.remove(connection);
				available.add(connection);

			}
		}

	
	public void close() {


		for (PooledConnection pooledConnection1 : used) {

			try {
				pooledConnection1.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}


		for (PooledConnection pooledConnection1 : available) {
			try {
				pooledConnection1.closeReally();

			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}

		}


	}
	
}

