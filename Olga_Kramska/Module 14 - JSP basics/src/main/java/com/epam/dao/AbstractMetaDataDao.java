package com.epam.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Olga Kramska on 28-May-16.
 */
public interface AbstractMetaDataDao<T, ID extends Serializable> extends RepositoryDao<T, ID> {
    List<T> getByUser(int userId);

    List<T> getByCookie(int cookieId);
}
