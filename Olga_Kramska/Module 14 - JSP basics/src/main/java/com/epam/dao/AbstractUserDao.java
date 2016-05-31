package com.epam.dao;

import java.io.Serializable;

/**
 * Created by Olga Kramska on 24-May-16.
 */
public interface AbstractUserDao<T, ID extends Serializable> extends RepositoryDao<T, ID> {

    T get(String name);
}
