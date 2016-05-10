package dao.impl;

import dao.DaoFactory;
import exception.DaoException;
import model.conf.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TableDaoTest {
	private static final String TABLE_NAME = "TEST";
	private static DaoFactory factory;
	private TableDao tableDao;

	@BeforeClass
	public static void init() throws DaoException {
		Configuration conf = Configuration.getInstance();
		conf.load("src/test/resources/configuration_test.properties");
		factory = DaoFactoryImpl.getInstance(true);
	}

	@Before
	public void setUp() {
		tableDao = factory.getTableDao();
	}

	@Test
	public void testCreateDelete() throws DaoException {
		tableDao.create(TABLE_NAME);
		tableDao.delete(TABLE_NAME);
	}

	@Test(expected = DaoException.class)
	public void testCreateException() throws DaoException {
		try {
			tableDao.create(TABLE_NAME);
			tableDao.create(TABLE_NAME);
		} finally {
			tableDao.delete(TABLE_NAME);
		}

	}

	@Test(expected = DaoException.class)
	public void testDelete() throws DaoException {
		tableDao.delete(TABLE_NAME);
	}
}