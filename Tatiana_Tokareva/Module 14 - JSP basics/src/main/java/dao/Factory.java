package dao;

import java.sql.Connection;

public interface Factory {


	Connection getConnection() throws InterruptedException;

	UserDao getUserDao(Connection connection);
	CookieDao getCookieDao(Connection connection);

	boolean closePool();


}
