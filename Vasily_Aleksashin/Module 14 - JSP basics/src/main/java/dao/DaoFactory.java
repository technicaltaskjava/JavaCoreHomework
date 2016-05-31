package dao;

import dao.impl.CookieDao;
import dao.impl.UserDao;
import exception.DaoException;

import javax.servlet.ServletContext;
import java.sql.Connection;

public interface DaoFactory {
	Connection getConnection() throws DaoException;

	void closeConnectionPool();

	int getAvailableConnectionsCount();

	void initDatabase(ServletContext context) throws DaoException;

	UserDao getUserDao();

	CookieDao getCookieDao();
}
