package homeworks.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ConnectionFactory {

	private final String url;
	private final String user;
	private final String pass;

	private static final Logger log = LoggerFactory.getLogger(ConnectionFactory.class);

	public ConnectionFactory(final String url, final String user, final String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("org.h2.Driver");

		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
