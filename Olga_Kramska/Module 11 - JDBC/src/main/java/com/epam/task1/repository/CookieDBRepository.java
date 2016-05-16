package com.epam.task1.repository;

import com.epam.model.Cookie;
import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Olga Kramska on 09-May-16.
 */
public class CookieDBRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookieDBRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO Cookie (COOKIE, PREDICTION) VALUES (?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM Cookie";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM Cookie WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Cookie SET COOKIE = ?, PREDICTION = ? WHERE ID = ?";

    private Connection connection;

    public CookieDBRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCookie(Cookie cookie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cookie.getName());
            preparedStatement.setString(2, cookie.getPrediction());
            preparedStatement.executeUpdate();
            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                cookie.setId(set.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    public Cookie getCookie(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    public void updateCookie(int id, String newCookie, String newPrediction) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, newCookie);
            preparedStatement.setString(2, newPrediction);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    public void updateUser(String oldCookie, String newCookie) {
        try (Statement preparedStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                if (oldCookie.equals(resultSet.getString("COOKIE"))) {
                    resultSet.updateString("COOKIE", newCookie);
                    resultSet.updateRow();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
