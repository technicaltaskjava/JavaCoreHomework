package com.epam.task2;

import com.epam.AbstractDaoTest;
import com.epam.model.Cookie;
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
public class CookieDaoTest extends AbstractDaoTest {

    @Test
    public void testGetCookie() throws SQLException {
        RepositoryDao<Cookie, Integer> cookieDao = DaoFactory.getCookieDao(dataSource().getConnection());
        Cookie cookie = cookieDao.get(1);
        assertNotNull(cookie);
        assertEquals("oatmeal", cookie.getName());
    }

    @Test
    public void testUpdateCookie() throws SQLException {
        final String cookieName = "biscuit";
        RepositoryDao<Cookie, Integer> cookieDao = DaoFactory.getCookieDao(dataSource().getConnection());
        Cookie cookie = cookieDao.get(1);
        cookie.setName(cookieName);
        cookieDao.update(cookie);
        assertEquals(cookieName, cookieDao.get(1).getName());
    }

    @Test
    public void testDeleteCookie() throws SQLException {
        RepositoryDao<Cookie, Integer> cookieDao = DaoFactory.getCookieDao(dataSource().getConnection());
        assertEquals(2, cookieDao.getAll().size());
        cookieDao.delete(1);
        assertEquals(1, cookieDao.getAll().size());
        assertNull(cookieDao.get(1));
    }
}
