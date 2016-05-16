package dao.impl;

import dao.DaoFactory;
import exception.DaoException;
import model.conf.Configuration;
import model.entity.Cookie;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class CookieDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(CookieDaoTest.class);
	private static DaoFactory factory;
	private final Cookie testCookie = new Cookie();
	private CookieDao cookieDao;

	@BeforeClass
	public static void init() throws DaoException {
		Configuration conf = Configuration.getInstance();
		conf.load("src/test/resources/configuration_test.properties");
		factory = DaoFactoryImpl.getInstance(true);
	}

	@Before
	public void setUp() {
		cookieDao = factory.getCookieDao();
		testCookie.setCookie("Test cookie");
		testCookie.setLuckyNumber(1000000);
	}

	@Test
	public void testGetAll() throws DaoException {
		final List<Cookie> cookies = cookieDao.getAll();
		assertFalse(cookies.isEmpty());
	}

	@Test
	public void testGetById() throws DaoException {
		assertNotNull(cookieDao.getById(1));
	}

	@Test(expected = DaoException.class)
	public void testGetByIdException() throws DaoException {
		cookieDao.getById(0);
	}

	@Test
	public void testAdd() throws DaoException {
		final Cookie actualCookie = cookieDao.add(testCookie);
		assertTrue(actualCookie.getId() == 11);
		cookieDao.delete(actualCookie);
	}

	@Test(expected = DaoException.class)
	public void testAddException() throws DaoException {
		final Cookie cookie = cookieDao.getById(1);
		cookieDao.add(cookie);
	}

	@Test
	public void testUpdate() throws DaoException {
		final List<Cookie> cookies = cookieDao.getAll();
		final Cookie cookie = cookies.get(cookies.size() - 1);
		cookie.setCookie("New Cookie");
		assertTrue(cookieDao.update(cookie) == 1);
	}

	@Test(expected = DaoException.class)
	public void testUpdateException() throws DaoException {
		cookieDao.update(new Cookie());
	}

	@Test
	public void testDelete() throws DaoException {
		Cookie cookie;
		try {
			cookie = cookieDao.getById(11);
		} catch (DaoException e) {
			logger.info(e.getMessage(), e);
			cookie = cookieDao.add(testCookie);
		}
		assertTrue(cookieDao.delete(cookie) == 1);
	}

	@Test(expected = DaoException.class)
	public void testDeleteException() throws DaoException {
		cookieDao.delete(new Cookie());
	}
}