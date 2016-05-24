package dao;

import entities.Cookie;
import entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuriy Krishtop on 22.05.2016.
 */
public class H2DaoFactory implements DaoFactory<Connection> {

    private Map<Class, DaoFactory.DaoCreator> creators;
    private ConnectionPool connectionPool;

    private static final Logger log = Logger.getLogger(H2DaoFactory.class);

    public H2DaoFactory() throws IOException {
        connectionPool = new ConnectionPool();
        try {
            Class.forName(Configuration.getInstance().getDrv());
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        creators = new HashMap<>();
        creators.put(Cookie.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new H2CookieDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new H2UserDao(connection);
            }
        });
    }

    @Override
    public Connection getContext() throws SQLException {
        return connectionPool.getConnectionFromPool();
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) {
        DaoCreator creator = creators.get(dtoClass);
        return creator.create(connection);
    }

}