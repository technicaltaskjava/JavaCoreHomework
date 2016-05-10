package dao.pool;

import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import model.conf.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PooledConnectionTest {
	private static final Logger logger = LoggerFactory.getLogger(PooledConnectionTest.class);

	private CustomConnectionPool pool;

	@Before
	public void setUp() throws InvalidKeyException {
		Configuration conf = Configuration.getInstance();
		conf.load("src/test/resources/configuration_test.properties");
		String url = conf.getProperty("db.url");
		String user = conf.getProperty("db.user");
		String pass = conf.getProperty("db.pass");
		pool = CustomConnectionPool.create(1, url, user, pass);
	}

	@After
	public void tearDown() {
		pool.dispose();
	}

	@Test
	public void testClose() throws DaoException {
		final Connection connection;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			connection.close();
			assertEquals(0, pool.getActiveConnections());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Test
	public void testRollback() throws SQLException {
		try {
			DaoFactoryImpl.getInstance(true);
			final Connection connection = pool.getConnection();
			connection.setAutoCommit(false);
			final String query = "INSERT INTO COOKIES (COOKIE, LUCKY_NUMBER) VALUES('Test cookie', 1000)";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.execute();
			}
			connection.close();
			assertTrue(connection.getAutoCommit());
		} catch (DaoException | SQLException e) {
			throw new SQLException(e.getMessage(), e);
		}

	}
}