package com.epam.task2;

import com.epam.AbstractDaoTest;
import com.epam.model.MetaData;
import com.epam.task2.dao.DaoFactory;
import com.epam.task2.dao.RepositoryDao;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class MetaDataDaoTest extends AbstractDaoTest {

    @Test
    public void testGetMetaData() throws SQLException {
        RepositoryDao<MetaData, Integer> metaDataDao = DaoFactory.getMetaDataDao(dataSource().getConnection());
        MetaData metaData = metaDataDao.get(1);
        assertNotNull(metaData);
        assertEquals(1, metaData.getCookieId());
        assertEquals(1, metaData.getUserId());
    }

    @Test
    public void testUpdateMetaData() throws SQLException {
        final int cookieId = 2;
        RepositoryDao<MetaData, Integer> metaDataDao = DaoFactory.getMetaDataDao(dataSource().getConnection());
        MetaData metaData = metaDataDao.get(1);
        metaData.setCookieId(cookieId);
        metaDataDao.update(metaData);
        assertEquals(cookieId, metaDataDao.get(1).getCookieId());
    }

    @Test
    public void testDeleteMetaData() throws SQLException {
        RepositoryDao<MetaData, Integer> metaDataDao = DaoFactory.getMetaDataDao(dataSource().getConnection());
        assertEquals(2, metaDataDao.getAll().size());
        metaDataDao.delete(1);
        assertEquals(1, metaDataDao.getAll().size());
        assertNull(metaDataDao.get(1));
    }
}
