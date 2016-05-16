package com.epam.task2;


import java.sql.SQLException;

public interface DaoFactory<T> {

    interface DaoCreator<T> {
        GenericDao create(T context);
    }

    T getContext() throws SQLException;

    GenericDao getDao(T context, Class dtoClass);
}
