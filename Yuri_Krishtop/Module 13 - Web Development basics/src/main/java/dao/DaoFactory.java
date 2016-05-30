package dao;

import java.sql.SQLException;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public interface DaoFactory<T> {

    interface DaoCreator<T> {
        GenericDao create(T context);
    }

    T getContext() throws SQLException;

    GenericDao getDao(T context, Class dtoClass);
}
