package dao.accessor;

import dao.entity.User;
import dao.exeption.AddUserException;
import dao.exeption.NoSuchUserException;
import dao.exeption.UpdateUserException;
import dao.exeption.UsersException;
import dao.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class UserAccessor {
    private ConnectionPool connectionPool;
    private String whereCondition = "";

    public UserAccessor(ConnectionPool pool) {
        this.connectionPool = pool;
    }

    public int size() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Users")) {

            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new UsersException(e);
        }

    }

    public User getUserByID(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRegDate(resultSet.getDate("reg_date"));

                return user;
            } else {
                throw new NoSuchUserException("No such user where id = " + id);
            }

        } catch (SQLException e) {
            throw new NoSuchUserException("No such user where id = " + id, e);
        }
    }

    public List<User> getUserList() {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users " + whereCondition)) {

            whereCondition = "";

            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRegDate(resultSet.getDate("reg_date"));

                userList.add(user);
            }

            return userList;
        } catch (SQLException e) {
            throw new NoSuchUserException("No users in the table", e);
        }
    }

    public List<User> getUserByFirstName(String firstName) {
        whereCondition = "where first_name = \'" + firstName + "\'";
        return getUserList();
    }

    public List<User> getUserBySecondName(String secondName) {
        whereCondition = "where second_name = \'" + secondName + "\'";
        return getUserList();
    }

    public List<User> getUserByRegDate(Date regDate) {
        whereCondition = "where reg_date =  \'" + regDate + "\'";
        return getUserList();
    }

    public List<User> getUserByEmail(String email) {
        whereCondition = "where email = \'" + email + "\'";
        return getUserList();
    }

    public void updateUser(User user) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Users SET first_name=?, second_name=?, email=?, reg_date=? where id=?")) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setDate(4, user.getRegDate());
            statement.setInt(5, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new UpdateUserException(e.getMessage(), e);
        }
    }

    public void addUser(User user) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users VALUES(?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setDate(6, user.getRegDate());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new AddUserException(e.getMessage(), e);
        }
    }

    public void deleteUserByID(User user) {
        deleteUserByID(user.getId());
    }

    public void deleteUserByID(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id=?")
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchUserException(e.getMessage(), e);
        }
    }

    public void deleteUserByFirstName(String name) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE first_name=?")
        ) {

            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new NoSuchUserException(e.getMessage(), e);
        }
    }

    public void close() {
        connectionPool.close();
    }
}
