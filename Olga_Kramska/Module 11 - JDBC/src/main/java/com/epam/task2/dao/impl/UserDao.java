package com.epam.task2.dao.impl;

import com.epam.model.User;
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
public class UserDao implements RepositoryDao<User, Integer> {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public User get(Integer id) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        ResultSet resultSet;
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
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
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatementUsers = connection.prepareStatement(DELETE_QUERY_USERS);
                 PreparedStatement preparedStatementMetaData = connection.prepareStatement(DELETE_QUERY_METADATA)) {
                preparedStatementMetaData.setInt(1, id);
                preparedStatementMetaData.executeUpdate();

                preparedStatementUsers.setInt(1, id);
                preparedStatementUsers.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
