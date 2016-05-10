package dao;

import dao.accessor.CookiesAccessor;
import dao.accessor.MetadataAccessor;
import dao.accessor.UserAccessor;
import dao.pool.ConnectionPool;

/**
 * @author Alexey Ushakov
 */
public class DAO {
    private static ConnectionPool pool;
    private static UserAccessor userAccessor;
    private static CookiesAccessor cookiesAccessor;
    private static MetadataAccessor metadataAccessor;

    private static DAO instance = new DAO();

    private DAO() {
        pool = new ConnectionPool("jdbc:h2:~/test", "sa", "");
        userAccessor = new UserAccessor(pool);
        cookiesAccessor = new CookiesAccessor(pool);
        metadataAccessor = new MetadataAccessor(pool);
    }

    public static DAO getInstance() {
        return instance;
    }

    public UserAccessor getUserAccessor() {
        return userAccessor;
    }

    public CookiesAccessor getCookiesAccessor() {
        return cookiesAccessor;
    }

    public MetadataAccessor getMetadataAccessor() {
        return metadataAccessor;
    }

    public ConnectionPool getConnectionPool() {
        return pool;
    }

    public void close() {
        userAccessor.close();
        cookiesAccessor.close();
        metadataAccessor.close();
    }
}
