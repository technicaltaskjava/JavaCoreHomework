package dao.impl;

import dao.DaoFactory;
import dao.pool.CustomConnectionPool;
import exception.DaoException;
import model.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.SqlScriptParser;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class DaoFactoryImpl implements DaoFactory {
	private static final Logger logger = LoggerFactory.getLogger(DaoFactoryImpl.class);
	private static DaoFactoryImpl instance = null;
	private CustomConnectionPool pool;
	private String driver;
	private String url;
	private String user;
	private String pass;
	private String sqlScriptFilePath;
	private int cp;
	private boolean isPoolDispose;

	private DaoFactoryImpl(final boolean isInitializeDatabase) throws DaoException {
		getConfig();
		try {
			Class.forName(driver);
			openConnectionPool();
			if (isInitializeDatabase) {
				initDatabase();
			}
		} catch (ClassNotFoundException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public static DaoFactory getInstance() throws DaoException {
		return DaoFactoryImpl.getInstance(false);
	}

	public static DaoFactory getInstance(final boolean initialDatabase) throws DaoException {
		if (instance == null) {
			instance = new DaoFactoryImpl(initialDatabase);
		}

		return instance;
	}

	@Override
	public Connection getConnection() throws DaoException {
		if (isPoolDispose) {
			openConnectionPool();
		}
		return pool.getConnection();
	}

	@Override
	public void openConnectionPool() {
		try {
			pool = CustomConnectionPool.create(cp, url, user, pass);
			isPoolDispose = false;
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage(), e);
		}
	}

	@Override
	public void closeConnectionPool() throws DaoException {
		final int activeConnections = pool.getActiveConnections();
		if (activeConnections == 0) {
			pool.dispose();
			isPoolDispose = true;
		} else {
			throw new DaoException(String.format("Can not close JDBC Connection Pool, still opened %s " +
					"connections", activeConnections));
		}
	}

	@Override
	public UserDao getUserDao() {
		return new UserDao(this);
	}

	@Override
	public CookieDao getCookieDao() {
		return new CookieDao(this);
	}

	@Override
	public MetadataDao getMetadataDao() {
		return new MetadataDao(this);
	}

	@Override
	public TableDao getTableDao() {
		return new TableDao(this);
	}

	private void getConfig() throws DaoException {
		try {
			Configuration conf = Configuration.getInstance();
			driver = conf.getProperty("db.driver");
			url = conf.getProperty("db.url");
			user = conf.getProperty("db.user");
			pass = conf.getProperty("db.pass");
			sqlScriptFilePath = conf.getProperty("sql.init");
			cp = Integer.parseInt(conf.getProperty("db.cp"));
		} catch (InvalidKeyException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	private void initDatabase() throws DaoException {
		BlockingQueue<String> sqlScripts;
		try {
			sqlScripts = SqlScriptParser.parse(sqlScriptFilePath);
		} catch (FileNotFoundException e) {
			throw new DaoException(e.getMessage(), e);
		}
		for (String script : sqlScripts) {
			try (Connection connection = getConnection();
			     PreparedStatement statement = connection.prepareStatement(script)) {
				connection.setAutoCommit(false);
				statement.execute();
				connection.commit();
			} catch (SQLException e) {
				throw new DaoException(e.getMessage(), e);
			}
		}
	}
}
