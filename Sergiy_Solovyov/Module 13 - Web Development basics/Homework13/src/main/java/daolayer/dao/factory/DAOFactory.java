package daolayer.dao.factory;

import daolayer.dao.CookieDAO;
import daolayer.dao.UserDAO;
import daolayer.exeptions.ConnectionPoolException;
import daolayer.pool.PooledConnection;

/**
 * @author Sergey Solovyov
 */
public abstract class DAOFactory {

    public static final int JDBC = 1;
    public static final int HIBERNATE = 2;

    public abstract PooledConnection getConnection() throws ConnectionPoolException;
    public abstract void returnConnection(PooledConnection connection);
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
