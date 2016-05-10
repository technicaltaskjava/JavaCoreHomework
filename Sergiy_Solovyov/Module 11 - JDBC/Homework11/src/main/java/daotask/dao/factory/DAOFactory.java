package daotask.dao.factory;

import daotask.dao.CookieDAO;
import daotask.dao.UserDAO;
import daotask.exeptions.ConnectionPoolException;

import java.sql.Connection;

/**
 * @author Sergey Solovyov
 */
public abstract class DAOFactory {

    public static final int JDBC = 1;
    public static final int HIBERNATE = 2;

    public abstract Connection getConnection() throws ConnectionPoolException;
    public abstract void returnConnection(Connection connection);
    public abstract void destroyPool();
    public abstract CookieDAO getCookieDAO() throws ConnectionPoolException;
    public abstract UserDAO getUserDAO() throws ConnectionPoolException;

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case JDBC:
                return new JDBCDaoFactory();
            case HIBERNATE :
                throw new UnsupportedOperationException();
            default:
                return null;
        }
    }
}
