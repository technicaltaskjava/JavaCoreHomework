package javase.dao.factoryh2;

import javase.connectionpool.ConnectionPool;
import javase.connectionpool.ConnectionPoolException;
import javase.dao.datasource.*;
import javase.dao.datasourceh2.*;
import javase.dao.factory.DAOFactory;

import java.sql.Connection;
/**
 * H2 Database Factory DAO interface
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public class H2DAOFactory implements DAOFactory {
    private ConnectionPool pool;

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool.getConnection();
    }

    @Override
    public void freeConnection(Connection connection) throws ConnectionPoolException {
        if (pool != null) {
            pool.retrieveConnection(connection);
        }
    }

    @Override
    public void closeFactory() throws ConnectionPoolException {
        pool.closeConnectionPool();
    }

    @Override
    public UsersDAO getUsersDAO(Connection connection) {
        return new H2UsersDAO(connection);
    }

    @Override
    public CookiesDAO getCookiesDAO(Connection connection) {
        return new H2CookiesDAO(connection);
    }
}
