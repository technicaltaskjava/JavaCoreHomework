package com.epam.mydao.dao;


import com.epam.mydao.user.Users;
import com.epam.mydao.exeptions.MyJDBCException;

import java.sql.*;


public class UserDAOJDBC implements UserDAO {
    private Statement statement;
    private ResultSet resultSet;

    public UserDAOJDBC(Connection connection) throws SQLException {
        statement = connection.createStatement();
    }


    private static String sqlFindForID(int id) {
        return "SELECT id, password, email  FROM Users WHERE id = " + id + ";";
    }

    private static String sqlFindForEmailPassword(String email, String password) {
        return "SELECT id, password, email  FROM Users  WHERE email = " + "'" + email + "'"
                + " AND password =" + "'" + password + "'" + ";";
    }

    private static String sqlChangePassword(Users user) {
        return "update USERS set PASSWORD =" + "'" + user.getPassword() + "'"
                + "where id = " + user.getId() + ";";
    }

    private static String sqlCreate(Users user) {
        return "INSERT INTO USERS (PASSWORD, EMAIL) values (" + "'" + user.getPassword() + "'"
                + ", " + "'" + user.getEmail() + "'" + ", " + ");";
    }

    private static String sqlUpdate(Users user) {
        return "UPDATE USERS SET PASSWORD = " + "'" + user.getPassword() + "' " + ",  "
                + "EMAIL = " + "'" + user.getEmail() + "'" + "WHERE id = " + user.getId() + ";";
    }

    private static String sqlDellet(Users user) {
        return "DELETE FROM USERS WHERE ID = " + user.getId() + ";";
    }

    private static String sqlExistEmail(String email) {
        return "SELECT id FROM Users WHERE email LIKE '" + email + "';";
    }


    @Override
    public Users findForID(int id) {
        return selectMath(sqlFindForID(id));
    }

    @Override
    public Users findForEmailPassword(String email, String password) {
        return selectMath(sqlFindForEmailPassword(email, password));
    }

    @Override
    public void changePassword(Users user) {
        try {
            int affectedRows = statement.executeUpdate(sqlChangePassword(user));

            if (affectedRows == 0)
                throw new MyJDBCException("Changing password failed, no rows affected.");
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    @Override
    public int getID(Users users) {
        return findForEmailPassword(users.getEmail(), users.getPassword()).getId();
    }

    @Override
    public void create(Users user) {
        try {
            statement.execute(sqlCreate(user));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        user.setId(findForEmailPassword(user.getEmail(), user.getPassword()).getId());
    }

    @Override
    public void update(Users user) {
        try {
            int affectedRows = statement.executeUpdate(sqlUpdate(user));
            if (affectedRows == 0) {
                throw new MyJDBCException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    @Override
    public void delete(Users user) {
        try {

            int affectedRows = statement.executeUpdate(sqlDellet(user));
            if (affectedRows == 0)
                throw new MyJDBCException("Deleting user failed, no rows affected.");
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
    }

    @Override
    public boolean existEmail(String email) {
        boolean exist;
        try {
            resultSet = statement.executeQuery(sqlExistEmail(email));
            exist = resultSet.next();
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }
        return exist;
    }

    private Users selectMath(String select) {
        Users user = null;
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

    private static Users map(ResultSet resultSet) {
        Users user;
        try {
            user = new Users(resultSet.getInt("ID"),
                    resultSet.getString("password"), resultSet.getString("email"));
        } catch (SQLException e) {
            throw new MyJDBCException(e);
        }

        return user;
    }


}
