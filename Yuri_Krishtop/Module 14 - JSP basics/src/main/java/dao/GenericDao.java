package dao;

import java.util.List;

/**
 * Created by Yuriy Krishtop on 20.05.2016.
 */
public interface GenericDao<T> {

    void insert(T object);

    T getById(int id);

    void deleteById(int id);

    List<T> getAll();

    int getCountElements();

}
