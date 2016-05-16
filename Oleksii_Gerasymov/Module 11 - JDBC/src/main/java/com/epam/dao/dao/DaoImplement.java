package com.epam.dao.dao;

import com.epam.dao.DbConnectionPool;
import com.epam.dao.objects.Cookie;
import com.epam.dao.objects.Metadata;
import com.epam.dao.objects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DaoImplement implements Dao {
    DbConnectionPool dbConnectionPool;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Logger log = Logger.getLogger(DaoImplement.class.getName());

    public DaoImplement() {
        try {
            dbConnectionPool = new DbConnectionPool();
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
        }
    }

    @Override
    public void closePool() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        dbConnectionPool.destroy();
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = dbConnectionPool.getConnection();
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
                users.add(user);
            }
            dbConnectionPool.freeConnection(connection);
            return users;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return users;
    }

    @Override
    public List<Cookie> getAllCookies() {
        Connection connection = dbConnectionPool.getConnection();
        List<Cookie> cookies = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from cookies");
            while (resultSet.next()) {
                Cookie cookie = new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));
                cookies.add(cookie);
            }
            dbConnectionPool.freeConnection(connection);
            return cookies;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return cookies;
    }

    @Override
    public List<Metadata> getAllMetadata() {
        Connection connection = dbConnectionPool.getConnection();
        List<Metadata> metadataList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from metadata");
            while (resultSet.next()) {
                Metadata metadata = new Metadata(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                metadataList.add(metadata);
            }
            dbConnectionPool.freeConnection(connection);
            return metadataList;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return metadataList;
    }

    @Override
    public User getUser(int id) {
        Connection connection = dbConnectionPool.getConnection();
        User user = null;
        try {
            preparedStatement = connection.prepareStatement("select * from users where id in (?)");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
            }
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
            return user;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return user;
    }

    @Override
    public Cookie getCookie(int id) {
        Connection connection = dbConnectionPool.getConnection();
        Cookie cookie = null;
        try {
            preparedStatement = connection.prepareStatement("select * from cookies where id in (?)");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cookie = new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));
            }
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
            return cookie;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return cookie;
    }

    @Override
    public List<User> getUserByName(String userName) {
        Connection connection = dbConnectionPool.getConnection();
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
                users.add(user);
            }
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
            return users;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return users;
    }

    @Override
    public List<Cookie> findCookieByText(String text) {
        Connection connection = dbConnectionPool.getConnection();
        List<Cookie> cookies = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from cookies where cookie like '%" + text + "%'"); //NOSONAR
            while (resultSet.next()) {
                Cookie cookie = new Cookie(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));
                cookies.add(cookie);
            }
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
            return cookies;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return cookies;
    }

    @Override
    public List<Metadata> findMetadata(int userId, int cookieId) {
        Connection connection = dbConnectionPool.getConnection();
        List<Metadata> metadataList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from metadata where cookie_id = ? and user_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, cookieId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Metadata metadata = new Metadata(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                metadataList.add(metadata);
            }
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
            return metadataList;
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
        return metadataList;
    }

    @Override
    public void addUser(User user) {
        Connection connection = dbConnectionPool.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into users (username, email, password, dateofbirth) values" +
                                                " ('" + user.getUserName() + "', '" + user.getEmail() + "', '" +
                                                user.getPassword() + "', '" + user.getDateOfBirth() +"');");
            dbConnectionPool.freeConnection(connection);
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    @Override
    public void addCookie(Cookie cookie) {
        Connection connection = dbConnectionPool.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into cookies (cookie, active) values" +" ('" + cookie.getCookieName() +
                    "', '" + cookie.isActive() +"');");
            dbConnectionPool.freeConnection(connection);
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    @Override
    public void addMetadata(Metadata metadata) {
        Connection connection = dbConnectionPool.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate("insert into metadata (cookie_id, user_id, time_added) values" +
                    " ('" + metadata.getUserId() + "', '" + metadata.getCookieId() + "', now());");
            dbConnectionPool.freeConnection(connection);
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    @Override
    public void deleteUser(int id) {
        Connection connection = dbConnectionPool.getConnection();
        try {
            preparedStatement = connection.prepareStatement("delete from metadata where user_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }

    @Override
    public void deleteCookie(int id) {
        Connection connection = dbConnectionPool.getConnection();
        try {
            preparedStatement = connection.prepareStatement("delete from metadata where cookie_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("delete from cookies where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnectionPool.freeConnection(connection);
        }
        catch (SQLException databaseException) {
            log.info(String.valueOf(databaseException));
        }
    }
}
