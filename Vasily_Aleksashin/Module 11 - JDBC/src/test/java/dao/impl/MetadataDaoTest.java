package dao.impl;

import dao.DaoFactory;
import exception.DaoException;
import model.conf.Configuration;
import model.entity.Cookie;
import model.entity.Metadata;
import model.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MetadataDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(MetadataDaoTest.class);
	private static DaoFactory factory;
	private Metadata metadataTest = new Metadata();

	private MetadataDao metadataDao;

	@BeforeClass
	public static void init() throws DaoException {
		Configuration conf = Configuration.getInstance();
		conf.load("src/test/resources/configuration_test.properties");
		factory = DaoFactoryImpl.getInstance(true);
	}

	@Before
	public void setUp() throws DaoException {
		metadataDao = factory.getMetadataDao();
		Cookie cookie = factory.getCookieDao().getById(1);
		User user = factory.getUserDao().getById(1);
		metadataTest.setCookie(cookie);
		metadataTest.setUser(user);
	}

	@Test
	public void testGetAll() throws DaoException {
		final List<Metadata> metadata = metadataDao.getAll();
		assertTrue(metadata.isEmpty());
	}

	@Test(expected = DaoException.class)
	public void testGetById() throws DaoException {
		metadataDao.getById(1);
	}

	@Test(expected = DaoException.class)
	public void testGetByIdException() throws DaoException {
		metadataDao.getById(0);
	}

	@Test
	public void testAdd() throws DaoException {
		final Metadata actualMetadata = metadataDao.add(metadataTest);
		assertTrue(actualMetadata.getId() == 1);
		metadataDao.delete(actualMetadata);
	}

	@Test(expected = DaoException.class)
	public void testAddException() throws DaoException {
		final Metadata metadata = metadataDao.getById(1);
		metadataDao.add(metadata);
	}

	@Test
	public void testUpdate() throws DaoException {
		final Metadata metadataAdded = metadataDao.add(metadataTest);
		final Cookie newCookie = factory.getCookieDao().getById(2);
		metadataAdded.setCookie(newCookie);
		assertTrue(metadataDao.update(metadataAdded) == 1);
	}

	@Test(expected = DaoException.class)
	public void testUpdateException() throws DaoException {
		metadataDao.update(new Metadata());
	}

	@Test
	public void testDelete() throws DaoException {
		Metadata metadata;
		try {
			metadata = metadataDao.getById(1);
		} catch (DaoException e) {
			logger.info(e.getMessage(), e);
			metadata = metadataDao.add(metadataTest);
		}
		assertTrue(metadataDao.delete(metadata) == 1);
	}

	@Test(expected = DaoException.class)
	public void testDeleteException() throws DaoException {
		metadataDao.delete(new Metadata());
	}
}