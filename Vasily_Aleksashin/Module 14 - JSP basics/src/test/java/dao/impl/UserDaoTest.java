package dao.impl;

import exception.DaoException;
import model.entity.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest extends AbstractDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private final User testUser = new User();
	private UserDao userDao;

	@BeforeClass
	public static void init() throws DaoException {
		initialize();
	}

	@AfterClass
	public static void destroy() {
		factory.closeConnectionPool();
	}

	@Before
	public void setUp() {
		userDao = factory.getUserDao();
		testUser.setUserName("Test");
		testUser.setEmail("test@mail.com");
		testUser.setPassword("123456");
		testUser.setFirstName("Test");
		testUser.setLastName("Test");
		testUser.setAge(50);
	}

	@Test
	public void testGetAll() throws DaoException {
		final List<User> users = userDao.getAll();
		assertFalse(users.isEmpty());
	}

	@Test
	public void testGetById() throws DaoException {
		assertNotNull(userDao.getById(1));
	}

	@Test(expected = DaoException.class)
	public void testGetByIdException() throws DaoException {
		userDao.getById(0);
	}

	@Test
	public void testAdd() throws DaoException {
		final User actualUser = userDao.add(testUser);
		assertTrue(actualUser.getId() == 2);
		userDao.delete(actualUser);
	}

	@Test(expected = DaoException.class)
	public void testAddException() throws DaoException {
		final User user = userDao.getById(1);
		userDao.add(user);
	}

	@Test
	public void testUpdate() throws DaoException {
		final List<User> users = userDao.getAll();
		final User user = users.get(users.size() - 1);
		user.setUserName("New User Name");
		assertTrue(userDao.update(user) == 1);
	}

	@Test(expected = DaoException.class)
	public void testUpdateException() throws DaoException {
		userDao.update(new User());
	}

	@Test
	public void testDelete() throws DaoException {
		User user;
		try {
			user = userDao.getById(2);
		} catch (DaoException e) {
			logger.info(e.getMessage(), e);
			user = userDao.add(testUser);
		}
		assertTrue(userDao.delete(user) == 1);
	}

	@Test(expected = DaoException.class)
	public void testDeleteException() throws DaoException {
		userDao.delete(new User());
	}
}