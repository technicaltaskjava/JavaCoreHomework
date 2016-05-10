package com.epam.task2.dao.impl;

import com.epam.model.Cookie;
import com.epam.task2.dao.RepositoryDao;
import com.epam.task2.exc.DataAccessException;

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
public class CookieDao implements RepositoryDao<Cookie, Integer> {
    private static final String INSERT_QUERY = "INSERT INTO Cookies(COOKIE, PREDICTION) VALUES (?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM Cookies";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM Cookies WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Cookies SET COOKIE = ?, PREDICTION = ? WHERE ID = ?";
    private static final String DELETE_QUERY_COOKIES = "DELETE FROM Cookies WHERE ID = ?";
    private static final String DELETE_QUERY_METADATA = "DELETE FROM MetaData WHERE COOKIE_ID  = ?";

    private Connection connection;

    public CookieDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Cookie cookie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cookie.getName());
            preparedStatement.setString(2, cookie.getPrediction());
            preparedStatement.executeUpdate();
            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                cookie.setId(set.getInt(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Cookie get(Integer id) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cookie> getAll() {
        ResultSet resultSet;
        List<Cookie> cookies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cookies.add(new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return cookies;
    }

    @Override
    public void update(Cookie cookie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, cookie.getName());
            preparedStatement.setString(2, cookie.getPrediction());
            preparedStatement.setInt(3, cookie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatementCookies = connection.prepareStatement(DELETE_QUERY_COOKIES);
            PreparedStatement preparedStatementMetaData = connection.prepareStatement(DELETE_QUERY_METADATA)) {
                preparedStatementMetaData.setInt(1, id);
                preparedStatementMetaData.executeUpdate();

                preparedStatementCookies.setInt(1, id);
                preparedStatementCookies.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
