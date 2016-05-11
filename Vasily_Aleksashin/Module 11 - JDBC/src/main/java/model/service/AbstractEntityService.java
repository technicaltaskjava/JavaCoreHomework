package model.service;

import dao.AbstractEntityDao;
import exception.DaoException;
import model.entity.Identified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class AbstractEntityService<T extends Identified, K extends AbstractEntityDao<T>> implements EntityService<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractEntityService.class);

	private final K entityDao;
	protected final List<T> entityList = new ArrayList<>();

	protected AbstractEntityService(final K entityDao) throws DaoException {
		this.entityDao = entityDao;
		entityList.addAll(this.entityDao.getAll());
	}

	@Override
	public T getById(final Integer id) {
		try {
			return entityList.get(id);
		} catch (IndexOutOfBoundsException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		return entityList.isEmpty() ? Collections.<T>emptyList() : Collections.unmodifiableList(entityList);
	}

	@Override
	public T add(final T entity) {
		if (entityList.contains(entity)) {
			return entity;
		}
		T entityWithId = null;
		try {
			entityWithId = entityDao.add(entity);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		if (entityWithId != null) {
			entityList.add(entityWithId);
		}
		return entityWithId;
	}

	@Override
	public int update(final T entity) {
		if (!entityList.contains(entity)) {
			return -1;
		}
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
		if (!entityList.contains(entity)) {
			return -1;
		}
		int rowCount = -1;
		try {
			rowCount = entityDao.delete(entity);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		if (rowCount != -1) {
			entityList.remove(entity);
		}
		return rowCount;
	}
}
