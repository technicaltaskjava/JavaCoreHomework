package daolayer.dao.factory;

import daolayer.dao.CookieDAO;
import daolayer.dao.UserDAO;
import daolayer.dao.impl.CookieDAOImpl;
import daolayer.dao.impl.UserDAOImpl;
import daolayer.exeptions.ConnectionPoolException;
import daolayer.pool.ConnectionPool;
import daolayer.pool.PooledConnection;

/**
 * @author Sergey Solovyov
 */
public class JDBCDaoFactory extends DAOFactory {

    private ConnectionPool pool;

    public JDBCDaoFactory() {
        this.pool = new ConnectionPool(5);
    }

    @Override
    public PooledConnection getConnection() throws ConnectionPoolException {
        return pool.retrieve();
    }

    @Override
    public void returnConnection(PooledConnection connection) {
       pool.putBack(connection);
    }

    @Override
    public void destroyPool() {
        pool.close();
    }

    @Override
    public CookieDAO getCookieDAO() throws ConnectionPoolException {
        return new CookieDAOImpl(getConnection());
    }

    @Override
    public UserDAO getUserDAO() throws ConnectionPoolException {
        return new UserDAOImpl(getConnection());
    }
}
