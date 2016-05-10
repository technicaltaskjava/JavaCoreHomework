package dao.pool;

import exception.DaoException;
import model.conf.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomConnectionPoolTest {
	private final Configuration conf = Configuration.getInstance();
	private CustomConnectionPool pool;

	@Before
	public void setUp() throws InvalidKeyException {
		conf.load("src/test/resources/configuration_test.properties");
		String url = conf.getProperty("db.url");
		String user = conf.getProperty("db.user");
		String pass = conf.getProperty("db.pass");
		pool = CustomConnectionPool.create(url, user, pass);
	}

	@After
	public void tearDown() {
		pool.dispose();
	}

	@Test
	public void testGetConnection() throws DaoException {
		assertNotNull(pool.getConnection());
	}

	@Test
	public void testGetActiveConnections() throws DaoException {
		assertEquals(0, pool.getActiveConnections());
	}

	@Test
	public void testGetAvailableConnections() throws DaoException {
		assertEquals(5, pool.getAvailableConnections());
	}

	@Test
	public void testReturnConnection() throws DaoException {
		final Connection connection = pool.getConnection();
		pool.returnConnection((PooledConnection) connection);
		assertEquals(0, pool.getActiveConnections());
	}
	
}