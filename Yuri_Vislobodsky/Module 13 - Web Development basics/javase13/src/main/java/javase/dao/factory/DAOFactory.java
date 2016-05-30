package javase.dao.factory;

import javase.connectionpool.ConnectionPoolException;
import javase.dao.datasource.*;
import java.sql.Connection;

/**
 * Abstract Factory DAO interface
 * Created by Yury Vislobodsky on 07.05.2016.
 */
public interface DAOFactory {
    Connection getConnection() throws ConnectionPoolException;
    void freeConnection(Connection connection) throws ConnectionPoolException;
    void closeFactory() throws ConnectionPoolException;
    UsersDAO getUsersDAO(Connection connection);
    CookiesDAO getCookiesDAO(Connection connection);
}
