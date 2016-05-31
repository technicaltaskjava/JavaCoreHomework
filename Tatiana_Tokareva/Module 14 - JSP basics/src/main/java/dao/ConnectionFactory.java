package dao;

import dao.pool.ConnectionPool;
import dao.substance.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;

public class ConnectionFactory implements Factory {

	 final String url;
	 final String user;
	 final String pass;
	private ConnectionPool pool;

	private static final Logger log = LoggerFactory.getLogger(ConnectionFactory.class);

	public ConnectionFactory(final String url, final String user, final String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;

		 pool = new ConnectionPool(url,user,pass);
		try {
			Class.forName("org.h2.Driver");

		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public Connection getConnection() throws InterruptedException {

		return pool.getConnection();


	}

	@Override
	public UserDao getUserDao(Connection connection) {
		return new UserDao(connection);
	}

	@Override
	public CookieDao getCookieDao(Connection connection) {
		return new CookieDao(connection);
	}
	@Override
	public boolean closePool() {
		pool.close();
		return false;
	}

}
