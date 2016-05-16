package com.epam.task2.dao.impl;

import com.epam.model.User;
import com.epam.task2.dao.RepositoryDao;
import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class UserDao implements RepositoryDao<User, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    private static final String INSERT_QUERY = "INSERT INTO Users (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM Users";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM Users WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Users SET USERNAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
    private static final String DELETE_QUERY_USERS = "DELETE FROM Users WHERE ID  = ?";
    private static final String DELETE_QUERY_METADATA = "DELETE FROM MetaData WHERE USER_ID  = ?";

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public User get(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement preparedStatement = connection.createStatement();
             ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection.setAutoCommit(false);
            delete(id, DELETE_QUERY_METADATA);
            delete(id, DELETE_QUERY_USERS);
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
    }

    private void delete(Integer id, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
