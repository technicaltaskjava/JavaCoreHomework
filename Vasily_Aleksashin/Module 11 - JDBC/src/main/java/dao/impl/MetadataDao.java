package dao.impl;

import dao.AbstractEntityDao;
import dao.DaoFactory;
import exception.DaoException;
import model.entity.Cookie;
import model.entity.Metadata;
import model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetadataDao extends AbstractEntityDao<Metadata> {
	public MetadataDao(DaoFactory factory) {
		super(factory);
	}

	@Override
	protected String getTable() {
		return "METADATA";
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Metadata metadata) throws DaoException {
		setParameter(statement, metadata);
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM METADATA WHERE ID = ?";
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Metadata metadata) throws DaoException {
		try {
			setParameter(statement, metadata);
			statement.setInt(3, metadata.getId());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public String getLastRow() {
		return "SELECT MAX(ID) FROM METADATA";
	}

	@Override
	protected List<Metadata> parseResultSet(ResultSet resultSet) throws DaoException {
		final List<Metadata> metadata = new ArrayList<>();
		try {
			while (resultSet.next()) {
				final EntityMetadata data = new EntityMetadata();
				data.setId(resultSet.getInt("ID"));
				final Cookie cookie = factory.getCookieDao().getById(resultSet.getInt("COOKIE_ID"));
				data.setCookie(cookie);
				final User user = factory.getUserDao().getById(resultSet.getInt("USER_ID"));
				data.setUser(user);
				data.setTimeAdded(resultSet.getTimestamp("TIME_ADDED"));
				metadata.add(data);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return metadata.isEmpty() ? Collections.<Metadata>emptyList() : metadata;
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE METADATA SET COOKIE_ID = ?, USER_ID = ? WHERE ID = ?";
	}

	private void setParameter(final PreparedStatement statement, final Metadata metadata) throws DaoException {
		try {
			statement.setInt(1, metadata.getCookie().getId());
			statement.setInt(2, metadata.getUser().getId());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public String getAddQuery() {
		return "INSERT INTO METADATA (COOKIE_ID, USER_ID, TIME_ADDED) VALUES (?, ?, CURRENT_TIMESTAMP)";
	}

	private class EntityMetadata extends Metadata {

		@Override
		protected void setId(final Integer id) { //NOSONAR this method protected entityList class from unexpected change
			// 'ID'
			super.setId(id);
		}
	}

	@Override
	public String getSelectQuery() {
		return "SELECT * FROM METADATA";
	}
}
