package daotask.dao.factory;

import daotask.dao.CookieDAO;
import daotask.dao.UserDAO;
import daotask.dao.impl.CookieDAOImpl;
import daotask.dao.impl.UserDAOImpl;
import daotask.exeptions.ConnectionPoolException;
import daotask.pool.ConnectionPool;
import daotask.pool.PooledConnection;

import java.sql.Connection;

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
