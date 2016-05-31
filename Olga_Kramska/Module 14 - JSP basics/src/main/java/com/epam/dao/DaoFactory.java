package com.epam.dao;

import com.epam.dao.impl.CookieDao;
import com.epam.dao.impl.MetaDataDao;
import com.epam.dao.impl.UserDao;
import com.epam.model.FortuneCookie;
import com.epam.model.MetaData;
import com.epam.model.User;

import javax.sql.DataSource;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public class DaoFactory {

    private DaoFactory() {
    }

    public static AbstractUserDao<User, Integer> getUserDao(DataSource source) {
        return new UserDao(source);
    }

    public static RepositoryDao<FortuneCookie, Integer> getCookieDao(DataSource source) {
        return new CookieDao(source);
    }

    public static AbstractMetaDataDao<MetaData, Integer> getMetaDataDao(DataSource source) {
        return new MetaDataDao(source);
    }
}
