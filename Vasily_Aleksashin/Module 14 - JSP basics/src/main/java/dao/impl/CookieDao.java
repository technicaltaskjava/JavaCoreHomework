package dao.impl;

import dao.AbstractEntityDao;
import dao.DaoFactory;
import exception.DaoException;
import model.entity.Cookie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CookieDao extends AbstractEntityDao<Cookie> {
	CookieDao(DaoFactory factory) {
		super(factory);
	}

	public List<Cookie> getCookieForPage(final int limit, final int offset) throws DaoException {
		String query = "SELECT * FROM COOKIES LIMIT ? OFFSET ?";
		List<Cookie> list;
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, limit);
			statement.setInt(2, offset);
			try (ResultSet resultSet = statement.executeQuery()) {
				list = parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return list == null ? Collections.<Cookie>emptyList() : list;
	}

	public int getRowCount() throws DaoException {
		String query = "SELECT COUNT(*) FROM COOKIES";
		int count = -1;
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return count;
	}

	@Override
	protected String getTable() {
		return "COOKIES";
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Cookie cookie) throws DaoException {
		setParameter(statement, cookie);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT * FROM COOKIES";
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Cookie cookie) throws DaoException {
		try {
			setParameter(statement, cookie);
			statement.setInt(3, cookie.getId());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public String getAddQuery() {
		return "INSERT INTO COOKIES (COOKIE, LUCKY_NUMBER) VALUES (?, ?)";
	}

	@Override
	protected List<Cookie> parseResultSet(ResultSet resultSet) throws DaoException {
		final List<Cookie> cookies = new ArrayList<>();
		try {
			while (resultSet.next()) {
				final EntityCookie cookie = new EntityCookie();
				cookie.setId(resultSet.getInt("ID"));
				cookie.setCookieMessage(resultSet.getString("COOKIE"));
				cookie.setLuckyNumber(resultSet.getInt("LUCKY_NUMBER"));
				cookies.add(cookie);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return cookies.isEmpty() ? Collections.<Cookie>emptyList() : cookies;

	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE COOKIES SET COOKIE = ?, LUCKY_NUMBER = ? WHERE ID = ?";
	}

	private void setParameter(final PreparedStatement statement, final Cookie cookie) throws DaoException {
		try {
			statement.setString(1, cookie.getCookieMessage());
			statement.setInt(2, cookie.getLuckyNumber());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM COOKIES WHERE ID = ?";
	}

	@Override
	public String getLastRow() {
		return "SELECT MAX(ID) FROM COOKIES";
	}

	public int getMaxId() throws DaoException {
		int maxId = -1;
		try (Connection connection = factory.getConnection();
		     PreparedStatement statement = connection.prepareStatement(getLastRow())) {
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					maxId = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return maxId;
	}

	private class EntityCookie extends Cookie {

		@Override
		protected void setId(final Integer id) { //NOSONAR this method protected entityList class from unexpected change 'ID'
			super.setId(id);
		}
	}
}
