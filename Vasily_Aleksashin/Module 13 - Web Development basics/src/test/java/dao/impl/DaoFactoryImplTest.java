package dao.impl;

import exception.DaoException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DaoFactoryImplTest extends AbstractDaoTest {
    @BeforeClass
    public static void init() throws DaoException {
        initialize();
    }

    @AfterClass
    public static void destroy() {
        factory.closeConnectionPool();
    }

    @Test
    public void testGetInstance() throws DaoException {
        assertTrue(factory == DaoFactoryImpl.getInstance());

    }

    @Test
    public void testGetConnection() throws DaoException {
        try (Connection connection = factory.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Test
    public void testGetAvailableConnectionsCount() throws InvalidKeyException {
        String property = configuration.getProperty("db.cp");
        int expected = Integer.parseInt(property);
        assertEquals(expected, factory.getAvailableConnectionsCount());
    }
}