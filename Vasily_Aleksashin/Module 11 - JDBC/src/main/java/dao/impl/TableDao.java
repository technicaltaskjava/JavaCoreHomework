package dao.impl;

import dao.DaoFactory;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableDao {
	private final DaoFactory factory;

	public TableDao(DaoFactory factory) {
		this.factory = factory;
	}

	public void create(final String name) throws DaoException {
		final String query = String.format("CREATE TABLE %s", name);
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			statement.execute();
		} catch (SQLException e) {
			throw new DaoException(String.format("Cannot create table %s", name), e);
		}
	}

	public void delete(final String name) throws DaoException {
		final String query = String.format("DROP TABLE %s", name);
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			statement.execute();
		} catch (SQLException e) {
			throw new DaoException(String.format("Cannot delete table %s", name), e);
		}
	}
}
