package dao;

import dao.impl.CookieDao;
import dao.impl.MetadataDao;
import dao.impl.TableDao;
import dao.impl.UserDao;
import exception.DaoException;

import java.sql.Connection;

public interface DaoFactory {
	Connection getConnection() throws DaoException;

	void openConnectionPool();

	void closeConnectionPool() throws DaoException;

	UserDao getUserDao();

	CookieDao getCookieDao();

	MetadataDao getMetadataDao();

	TableDao getTableDao();
}
