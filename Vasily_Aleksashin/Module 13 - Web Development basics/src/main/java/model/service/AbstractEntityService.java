package model.service;

import dao.AbstractEntityDao;
import exception.DaoException;
import model.entity.Identified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;


public abstract class AbstractEntityService<T extends Identified, K extends AbstractEntityDao<T>> implements EntityService<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractEntityService.class);
    private final K entityDao;

    protected AbstractEntityService(final K entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    public T getById(final Integer id) {
        try {
            return entityDao.getById(id);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> entityList = null;
        try {
            entityList = entityDao.getAll();
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
        return entityList == null || entityList.isEmpty() ? Collections.<T>emptyList() : Collections.unmodifiableList(entityList);
    }

    @Override
    public T add(final T entity) {
        T entityWithId = null;
        try {
            entityWithId = entityDao.add(entity);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
        return entityWithId == null ? entity : entityWithId;
    }

    @Override
    public int update(final T entity) {
        int rowCount = -1;
        try {
            rowCount = entityDao.update(entity);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
        return rowCount;
    }

    @Override
    public int delete(final T entity) {
        int rowCount = -1;
        try {
            rowCount = entityDao.delete(entity);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
        return rowCount;
    }
}
