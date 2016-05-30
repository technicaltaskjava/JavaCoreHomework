package dao.impl;

import dao.DaoFactory;
import exception.DaoException;
import model.conf.Configuration;
import org.h2.jdbcx.JdbcConnectionPool;
import utility.SqlScriptParser;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class DaoFactoryImpl implements DaoFactory {
    private static DaoFactoryImpl instance = null;
    private JdbcConnectionPool pool;
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String sqlScriptFilePath;
    private int cp;
    private boolean isDispose;

    private DaoFactoryImpl() throws DaoException {
        getConfig();
        try {
            Class.forName(driver);
            createConnectionPool();
        } catch (ClassNotFoundException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public static DaoFactory getInstance() throws DaoException {
        if (instance == null) {
            instance = new DaoFactoryImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws DaoException {
        Connection connection;
        if (isDispose) {
            createConnectionPool();
        }
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return connection;
    }

    @Override
    public void closeConnectionPool() {
        pool.dispose();
        isDispose = true;
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
    public int getAvailableConnectionsCount() {
        return pool.getMaxConnections() - pool.getActiveConnections();
    }


    @Override
    public void initDatabase(final ServletContext context) throws DaoException {
        BlockingQueue<String> sqlScripts;
        try {
            URL resource = context.getResource(sqlScriptFilePath);
            sqlScripts = SqlScriptParser.parse(resource.toURI());
        } catch (FileNotFoundException | URISyntaxException | NullPointerException | MalformedURLException e) {
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

    private void createConnectionPool() {
        pool = JdbcConnectionPool.create(url, user, pass);
        pool.setMaxConnections(cp);
        isDispose = false;
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
}
