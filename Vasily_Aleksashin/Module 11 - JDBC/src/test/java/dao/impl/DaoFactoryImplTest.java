package dao.impl;

import dao.DaoFactory;
import exception.DaoException;
import model.conf.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class DaoFactoryImplTest {
	private static final Logger logger = LoggerFactory.getLogger(DaoFactoryImplTest.class);
	private final Configuration conf = Configuration.getInstance();
	private DaoFactory factory;

	@Before
	public void setUp() throws DaoException {
		conf.load("src/test/resources/configuration_test.properties");
		factory = DaoFactoryImpl.getInstance(true);
	}

	@Test
	public void testGetConnection() throws DaoException {
		try (Connection connection = factory.getConnection()) {
			assertNotNull(connection);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Test(expected = DaoException.class)
	public void testGetConnectionCount() throws DaoException {
		int connectionCount = 0;
		try {
			connectionCount = Integer.parseInt(Configuration.getInstance().getProperty("db.cp"));
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		}
		Connection[] connections = new Connection[connectionCount];
		Connection connection = null;
		try {
			for (int index = 0; index < connectionCount; index++) {
				connections[index] = factory.getConnection();
			}
			connection = factory.getConnection();
			fail();
		} finally {
			for (int index = 0; index < connectionCount; index++) {
				try {
					connections[index].close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	@Test
	public void testCloseConnectionPool() throws DaoException {
		final Connection connection = factory.getConnection();
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		factory.closeConnectionPool();
	}

	@Test(expected = DaoException.class)
	public void testCloseConnectionPoolWithOpenConnection() throws DaoException {
		try (Connection connection = factory.getConnection()) {
			if (!connection.isClosed()) {
				factory.closeConnectionPool();
			} else {
				fail();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}