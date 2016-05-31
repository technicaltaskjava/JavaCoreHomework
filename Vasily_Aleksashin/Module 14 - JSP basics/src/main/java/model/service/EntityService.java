package model.service;

import model.entity.Identified;

import java.util.List;

interface EntityService<T extends Identified> {
	T getById(Integer id);

	List<T> getAll();

	T add(T entity);

	int update(T entity);

	int delete(T entity);
}
