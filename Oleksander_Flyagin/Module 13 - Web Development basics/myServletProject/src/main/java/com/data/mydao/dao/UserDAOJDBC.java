package com.data.mydao.dao;


import com.data.mydao.exeptions.MyJDBCException;
import com.data.users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC implements UserDAO {

    private Statement statement;
    private ResultSet resultSet;


    public UserDAOJDBC(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }


    private static String sqlFindForLogin(String login) {
        return "SELECT *  FROM USERS WHERE LOGIN = '" + login + "';";
    }

    private static String sqlFindForLoginPassword(String login, String password) {
        return "SELECT *  FROM USERS WHERE LOGIN = '" + login + "' AND PASSWORD = '" + password + "';";
    }

    private static String sqlCreate(User user) {
        return "INSERT INTO USERS (LOGIN,PASSWORD, EMAIL) values ('" + user.getLogin() + "', '"
                + user.getPassword() + "', '" + user.getEmail() + "');";
    }

    private static String sqlUpdate(User user) {
        return "UPDATE USERS SET PASSWORD = " + "'" + user.getPassword() + "' " + ",  "
                + "EMAIL = " + "'" + user.getEmail() + "'" + "WHERE LOGIN = '" + user.getLogin() + "';";
    }


    private static String sqlDelete(User user) {
        return "DELETE FROM USERS WHERE LOGIN = '" + user.getLogin() + "';";
    }


    private static String sqlExistLogin(String login) {
        return "SELECT LOGIN FROM USERS WHERE LOGIN LIKE '" + login + "';";
    }

    private static String sqlUsersList() {
        return " SELECT * from USERS;";
    }


    private static String sqlCountUsers() {
        return " SELECT COUNT(LOGIN)FROM USERS;";
    }

    @Override
    public User findForLogin(String login) {
        return selectMath(sqlFindForLogin(login));
    }

    @Override
    public User findForLoginPassword(String login, String password) {
        return selectMath(sqlFindForLoginPassword(login, password));
    }

    @Override
    public void create(User user) {
        try {
            statement.execute(sqlCreate(user));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        usersList().add(user);
    }

    @Override
    public void update(User user) {
        try {
            int affectedRows = statement.executeUpdate(sqlUpdate(user));
            if (affectedRows == 0) {
                throw new MyJDBCException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        for (User users : usersList()) {
            if (users.getLogin().equals(user.getLogin())) {
                users = user;
            }
        }
    }

    @Override
    public void delete(User user) {
        try {

            int affectedRows = statement.executeUpdate(sqlDelete(user));
            if (affectedRows == 0)
                throw new MyJDBCException("Deleting user failed, no rows affected.");
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        usersList().remove(user);
    }

    @Override
    public boolean existLogin(String login) {
        boolean exist;
        try {
            resultSet = statement.executeQuery(sqlExistLogin(login));
            exist = resultSet.next();
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return exist;
    }


    @Override
    public List<User> usersList() {
         List<User> users = new ArrayList();
        try {
            resultSet = statement.executeQuery(sqlUsersList());
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return users;
    }

    @Override
    public int countUsers() {
        int resoult= 0;
        try {
            resultSet = statement.executeQuery(sqlCountUsers());
            while (resultSet.next()) {
                resoult = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return resoult;
    }

    private User selectMath(String select) {
        User user = null;
        try {
            resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                user = map(resultSet);
            }

        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

        return user;
    }


    private static User map(ResultSet resultSet) {
        User user;
        try {
            user = new User(
                    resultSet.getString("LOGIN"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("EMAIL")
            );
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

        return user;
    }
}
