package javase.t02.dao.factory;

import javase.common.connectionpool.ConnectionPoolException;
import javase.t02.dao.datasource.*;
import java.sql.Connection;

/**
 * Abstract Factory DAO interface
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface DAOFactory {
    Connection getConnection() throws ConnectionPoolException;
    void freeConnection(Connection connection) throws ConnectionPoolException;
    UsersDAO getUsersDAO(Connection connection);
    CookiesDAO getCookiesDAO(Connection connection);
    MetadataDAO getMetadataDAO(Connection connection);
}
