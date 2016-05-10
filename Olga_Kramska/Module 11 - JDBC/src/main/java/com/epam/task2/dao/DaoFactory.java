package com.epam.task2.dao;

import com.epam.model.Cookie;
import com.epam.model.MetaData;
import com.epam.model.User;
import com.epam.task2.dao.impl.CookieDao;
import com.epam.task2.dao.impl.MetaDataDao;
import com.epam.task2.dao.impl.UserDao;
import com.epam.task2.exc.DataAccessException;
import com.epam.task2.util.ConnectionPool;
import com.epam.task2.util.PropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public class DaoFactory {

    private DaoFactory() {
    }

    private static Connection getConnection() {

        PropertiesReader.load("database.properties");
        String driver = PropertiesReader.getProperty("jdbc.drivers");
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }
        String url = PropertiesReader.getProperty("jdbc.url");
        String username = PropertiesReader.getProperty("jdbc.username");
        String password = PropertiesReader.getProperty("jdbc.password");

        ConnectionPool connectionPool = new ConnectionPool(url, username, password);
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }


    public static RepositoryDao<User, Integer> getUserDao(Connection connection) {
        return new UserDao(connection);
    }

    public static RepositoryDao<Cookie, Integer> getCookieDao(Connection connection) {
        return new CookieDao(connection);
    }

    public static RepositoryDao<MetaData, Integer> getMetaDataDao(Connection connection) {
        return new MetaDataDao(connection);
    }
}
