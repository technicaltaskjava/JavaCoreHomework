package dao.impl;

import dao.AbstractEntityDao;
import dao.DaoFactory;
import exception.DaoException;
import model.entity.Cookie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CookieDao extends AbstractEntityDao<Cookie> {
	public CookieDao(DaoFactory factory) {
		super(factory);
	}

	@Override
	public String getSelectQuery() {
		return "SELECT * FROM COOKIES";
	}

	@Override
	public String getAddQuery() {
		return "INSERT INTO COOKIES (COOKIE, LUCKY_NUMBER) VALUES (?, ?)";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE COOKIES SET COOKIE = ?, LUCKY_NUMBER = ? WHERE ID = ?";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM COOKIES WHERE ID = ?";
	}

	@Override
	public String getLastRow() {
		return "SELECT MAX(ID) FROM COOKIES";
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
	protected void prepareStatementForUpdate(PreparedStatement statement, Cookie cookie) throws DaoException {
		try {
			setParameter(statement, cookie);
			statement.setInt(3, cookie.getId());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	protected List<Cookie> parseResultSet(ResultSet resultSet) throws DaoException {
		final List<Cookie> cookies = new ArrayList<>();
		try {
			while (resultSet.next()) {
				final EntityCookie cookie = new EntityCookie();
				cookie.setId(resultSet.getInt("ID"));
				cookie.setCookie(resultSet.getString("COOKIE"));
				cookie.setLuckyNumber(resultSet.getInt("LUCKY_NUMBER"));
				cookies.add(cookie);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return cookies.isEmpty() ? Collections.<Cookie>emptyList() : cookies;

	}

	private void setParameter(final PreparedStatement statement, final Cookie cookie) throws DaoException {
		try {
			statement.setString(1, cookie.getCookie());
			statement.setInt(2, cookie.getLuckyNumber());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	private class EntityCookie extends Cookie {
		@Override
		protected void setId(final Integer id) { //NOSONAR this method protected entityList class from unexpected change 'ID'
			super.setId(id);
		}
	}
}
