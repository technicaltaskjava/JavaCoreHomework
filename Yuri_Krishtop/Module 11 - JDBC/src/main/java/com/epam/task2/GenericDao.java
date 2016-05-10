package com.epam.task2;

import java.util.List;

public interface GenericDao<T> {

    void create(T object);

    void persist(T object);

    T getById(int id);

    void update(T object);

    void delete(T object);

    List<T> getAll();
}
