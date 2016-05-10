package db.access.dao;

import java.sql.Connection;

public abstract class AbstractAccessor implements DataAccessObject {

    protected Connection connection;

    public AbstractAccessor(Connection connection){
        this.connection = connection;
    }

    @Override
    public Connection getConnection(){
        Connection temp = connection;
        connection = null;
        return temp;
    }
}