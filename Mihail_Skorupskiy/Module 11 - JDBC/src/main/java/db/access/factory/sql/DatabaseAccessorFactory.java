package db.access.factory.sql;

import db.access.dao.CookieAccessor;
import db.access.dao.DataAccessObject;
import db.access.dao.MetadataAccessor;
import db.access.dao.UserAccessor;
import db.access.factory.DAOFactory;
import db.connection.MyConnectionPool;
import db.exceptions.DAOCreationException;
import db.exceptions.InvalidDataTypeException;
import db.storage.DataTypes;

import java.sql.SQLException;

public class DatabaseAccessorFactory implements DAOFactory {

    private MyConnectionPool pool;

    public DatabaseAccessorFactory(MyConnectionPool pool){
        this.pool = pool;
    }

    @Override
    public synchronized DataAccessObject getDAO(DataTypes type) throws DAOCreationException{
        try {
            switch (type) {
                case COOKIE:
                    return new CookieAccessor(pool.getConnection());
                case USER:
                    return new UserAccessor(pool.getConnection());
                case METADATA:
                    return new MetadataAccessor(pool.getConnection());
                default:
                    throw new InvalidDataTypeException();
            }
        } catch (Exception e){
            throw new DAOCreationException(e);
        }
    }

    @Override
    public void close(DataAccessObject dao) throws SQLException{
        pool.put(dao.getConnection());
    }
}
