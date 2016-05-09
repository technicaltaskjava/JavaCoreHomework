package javase.t02.dao.factoryh2;

import javase.common.connectionpool.ConnectionPool;
import javase.common.connectionpool.ConnectionPoolException;
import javase.t02.dao.datasource.*;
import javase.t02.dao.datasourceh2.*;
import javase.t02.dao.factory.DAOFactory;

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
    public UsersDAO getUsersDAO(Connection connection) {
        return new H2UsersDAO(connection);
    }

    @Override
    public CookiesDAO getCookiesDAO(Connection connection) {
        return new H2CookiesDAO(connection);
    }

    @Override
    public MetadataDAO getMetadataDAO(Connection connection) {
        return new H2MetadataDAO(connection);
    }
}
