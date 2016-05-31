package com.epam.dao.impl;

import com.epam.dao.RepositoryDao;
import com.epam.exc.DataAccessException;
import com.epam.model.FortuneCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Kramska on 10-May-16.
 */
public class CookieDao implements RepositoryDao<FortuneCookie, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieDao.class);

    private static final String INSERT_QUERY = "INSERT INTO Cookies(COOKIE, PREDICTION) VALUES (?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM Cookies";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM Cookies WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Cookies SET COOKIE = ?, PREDICTION = ? WHERE ID = ?";
    private static final String DELETE_QUERY_COOKIES = "DELETE FROM Cookies WHERE ID = ?";
    private static final String DELETE_QUERY_METADATA = "DELETE FROM MetaData WHERE COOKIE_ID  = ?";

    private DataSource source;

    public CookieDao(DataSource source) {
        this.source = source;
    }

    @Override
    public void add(FortuneCookie cookie) {
        try (PreparedStatement preparedStatement = source.getConnection().
                prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cookie.getName());
            preparedStatement.setString(2, cookie.getPrediction());
            preparedStatement.executeUpdate();
            try (ResultSet set = preparedStatement.getGeneratedKeys()) {
                if (set.next()) {
                    cookie.setId(set.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public FortuneCookie get(Integer id) {
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new FortuneCookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<FortuneCookie> getAll() {
        List<FortuneCookie> cookies = new ArrayList<>();
        try (Statement preparedStatement = source.getConnection().createStatement();
             ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                cookies.add(new FortuneCookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return cookies;
    }

    @Override
    public void update(FortuneCookie cookie) {
        try (PreparedStatement preparedStatement = source.getConnection().prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, cookie.getName());
            preparedStatement.setString(2, cookie.getPrediction());
            preparedStatement.setInt(3, cookie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = source.getConnection()) {
            try {
                connection.setAutoCommit(false);
                delete(connection, id, DELETE_QUERY_METADATA);
                delete(connection, id, DELETE_QUERY_COOKIES);
                connection.commit();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    LOGGER.error(e1.getMessage(), e1);
                }
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void delete(Connection connection, Integer id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
