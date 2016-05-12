package com.epam.task2.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public interface RepositoryDao<T, ID extends Serializable> {//NOSONAR

    void add(T entity);

    T get(ID id);

    List<T> getAll();

    void update(T entity);

    void delete(ID id);
}
