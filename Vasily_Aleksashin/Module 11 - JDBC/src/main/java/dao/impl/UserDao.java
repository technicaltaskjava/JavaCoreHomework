package dao.impl;

import dao.AbstractEntityDao;
import dao.DaoFactory;
import exception.DaoException;
import model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDao extends AbstractEntityDao<User> {

	public UserDao(final DaoFactory factory) {
		super(factory);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT * FROM USERS";
	}

	@Override
	public String getAddQuery() {
		return "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, AGE) VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE USERS SET USERNAME = ?, EMAIL = ?, PASSWORD = ?, FIRST_NAME = ?, LAST_NAME = ?, AGE = ? WHERE ID = ?";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM USERS WHERE ID = ?";
	}

	@Override
	public String getLastRow() {
		return "SELECT MAX(ID) FROM USERS";
	}

	@Override
	protected String getTable() {
		return "USERS";
	}

	@Override
	protected void prepareStatementForInsert(final PreparedStatement statement, final User user) throws DaoException {
		setParameter(statement, user);
	}

	@Override
	protected void prepareStatementForUpdate(final PreparedStatement statement, final User user) throws DaoException {
		try {
			setParameter(statement, user);
			statement.setInt(7, user.getId());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	protected List<User> parseResultSet(final ResultSet resultSet) throws DaoException {
		final List<User> users = new ArrayList<>();
		try {
			while (resultSet.next()) {
				final EntityUser user = new EntityUser();
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setEmail(resultSet.getString("EMAIL"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setFirstName(resultSet.getString("FIRST_NAME"));
				user.setLastName(resultSet.getString("LAST_NAME"));
				user.setAge(resultSet.getInt("AGE"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return users.isEmpty() ? Collections.<User>emptyList() : users;
	}

	private void setParameter(final PreparedStatement statement, final User user) throws DaoException {
		try {
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setInt(6, user.getAge());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	private class EntityUser extends User {
		@Override
		protected void setId(final Integer id) { //NOSONAR this method protected entityList class from unexpected change
			// 'ID'
			super.setId(id);
		}
	}
}
