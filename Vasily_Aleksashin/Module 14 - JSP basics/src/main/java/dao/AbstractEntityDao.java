package dao;

import exception.DaoException;
import model.entity.Identified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public abstract class AbstractEntityDao<T extends Identified> implements EntityDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractEntityDao.class);

	protected final DaoFactory factory;

	protected AbstractEntityDao(final DaoFactory factory) {
		this.factory = factory;
	}

	protected abstract String getSelectQuery();

	protected abstract String getAddQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract String getLastRow();

	protected abstract String getTable();

	@Override
	public T getById(final Integer id) throws DaoException {
		Validator.isNull(id, "ID");
		List<T> list;
		final String query = String.format("%s WHERE ID = ?", getSelectQuery());
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				list = parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		if (list == null || list.isEmpty()) {
			throw new DaoException(String.format("According to ID %s not found", id));
		}
		if (list.size() > 1) {
			throw new DaoException("Received more than one record");
		}
		return list.iterator().next();
	}

	@Override
	public List<T> getAll() throws DaoException {
		String query = getSelectQuery();
		List<T> list;
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				list = parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return list == null ? Collections.<T>emptyList() : list;
	}

	@Override
	public T add(final T entity) throws DaoException {
		Validator.isNull(entity);
		if (entity.getId() != null) {
			throw new DaoException(String.format("%s already exist", entity));
		}
		String query = getAddQuery();
		T entityInstance;
		try (Connection connection = factory.getConnection()) {
			connection.setAutoCommit(false);
			int count;
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				prepareStatementForInsert(statement, entity);
				count = statement.executeUpdate();
				if (count != 1) {
					throw new DaoException(String.format("Can not added %s into table", entity));
				}
				connection.commit();
			}
			entityInstance = getLast();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return entityInstance;
	}

	@Override
	public int delete(final T entity) throws DaoException {
		Validator.isNull(entity);
		if (entity.getId() == null) {
			throw new DaoException(String.format("%s not exist", entity));
		}
		String query = getDeleteQuery();
		try (Connection connection = factory.getConnection()) {
			connection.setAutoCommit(false);
			int count;
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, entity.getId());
				count = statement.executeUpdate();
				if (count != 1) {
					throw new DaoException(String.format("Can not delete %s into table", entity));
				}
				connection.commit();
			}
			fixId();
			return count;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public int update(final T entity) throws DaoException {
		Validator.isNull(entity);
		if (entity.getId() == null) {
			throw new DaoException(String.format("%s not exist", entity));
		}
		String query = getUpdateQuery();
		try (Connection connection = factory.getConnection()) {
			connection.setAutoCommit(false);
			int count;
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				prepareStatementForUpdate(statement, entity);
				count = statement.executeUpdate();
				if (count != 1) {
					throw new DaoException(String.format("Can not updated %s into table", entity));
				}
				connection.commit();
			}
			return count;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public T getLast() throws DaoException {
		List<T> list;
		String query = String.format("%s WHERE ID = %s", getSelectQuery(), getLastRow());
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				list = parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		if (list == null || list.isEmpty()) {
			throw new DaoException("Table is empty or not exist");
		}
		return list.iterator().next();
	}

	protected abstract void prepareStatementForInsert(final PreparedStatement statement, final T entity) throws DaoException;

	protected abstract void prepareStatementForUpdate(final PreparedStatement statement, final T entity) throws DaoException;

	protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;

	private void fixId() throws DaoException {
		T lastEntity = null;
		try {
			lastEntity = getLast();
		} catch (DaoException e) {
			logger.info(e.getMessage(), e);
		}
		int index = 1;
		if (lastEntity != null) {
			index += lastEntity.getId();
		}
		final String query = String.format("ALTER TABLE %s ALTER COLUMN ID RESTART WITH ?", getTable());
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, index);
			statement.execute();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
