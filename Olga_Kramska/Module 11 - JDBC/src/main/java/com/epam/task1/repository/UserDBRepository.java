package com.epam.task1.repository;

import com.epam.model.User;
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
 * Created by Olga Kramska on 09-May-16.
 */
public class UserDBRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDBRepository.class);

    private static final String INSERT_QUERY = "INSERT INTO Users (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM Users";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM Users WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Users SET USERNAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM Users WHERE ID  = ?";

    private Connection connection;

    public UserDBRepository(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return users;
    }

    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                user.setId(set.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    public User getUser(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
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

    public void updateUser(String oldUserName, String newUserName) throws SQLException {
        try (Statement preparedStatement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_QUERY)) {
            while (resultSet.next()) {
                if (oldUserName.equals(resultSet.getString("USERNAME"))) {
                    resultSet.updateString("USERNAME", newUserName);
                    resultSet.updateRow();
                }
            }
        }
    }

    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
