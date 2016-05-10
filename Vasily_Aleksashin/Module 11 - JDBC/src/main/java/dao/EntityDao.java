package dao;

import exception.DaoException;
import model.entity.Identified;

import java.util.List;

public interface EntityDao<T extends Identified> {
	T getById(Integer id) throws DaoException;

	List<T> getAll() throws DaoException;

	T add(T entity) throws DaoException;

	int delete(T entity) throws DaoException;

	int update(T entity) throws DaoException;

	T getLast() throws DaoException;
}
