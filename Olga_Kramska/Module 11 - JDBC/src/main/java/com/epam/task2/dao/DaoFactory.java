package com.epam.task2.dao;

import com.epam.model.Cookie;
import com.epam.model.MetaData;
import com.epam.model.User;
import com.epam.task2.dao.impl.CookieDao;
import com.epam.task2.dao.impl.MetaDataDao;
import com.epam.task2.dao.impl.UserDao;

import java.sql.Connection;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public class DaoFactory {

    private DaoFactory() {
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
