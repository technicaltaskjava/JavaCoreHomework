package com.epam.task2;

import com.epam.AbstractDaoTest;
import com.epam.model.User;
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
public class UserDaoTest extends AbstractDaoTest {

    @Test
    public void testGetUser() throws SQLException {
        RepositoryDao<User, Integer> usersDao = DaoFactory.getUserDao(dataSource().getConnection());
        User user = usersDao.get(1);
        assertNotNull(user);
        assertEquals("John", user.getUsername());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        final String name = "Joe";
        RepositoryDao<User, Integer> usersDao = DaoFactory.getUserDao(dataSource().getConnection());
        User user = usersDao.get(1);
        user.setUsername(name);
        usersDao.update(user);
        assertEquals(name, usersDao.get(1).getUsername());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        RepositoryDao<User, Integer> usersDao = DaoFactory.getUserDao(dataSource().getConnection());
        assertEquals(2, usersDao.getAll().size());
        usersDao.delete(1);
        assertEquals(1, usersDao.getAll().size());
        assertNull(usersDao.get(1));
    }
}
