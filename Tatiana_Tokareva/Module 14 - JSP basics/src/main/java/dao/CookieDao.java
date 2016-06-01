package dao;


import dao.substance.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CookieDao {

    private Connection connection;
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public CookieDao(Connection connection) {
        this.connection = connection;
    }


    public List<Cookie> selectAll(String tableName) {

        LinkedList<Cookie> cookies = new LinkedList<>();

        String select = String.format("SELECT * FROM %s ", tableName);
        try (PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Cookie cookie = takeCookie(resultSet);
                cookies.add(cookie);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return cookies;
    }

    public List<Cookie> pagesSelect (int limit, int offset) {

        LinkedList<Cookie> cookies = new LinkedList<>();

        String select = String.format("SELECT * FROM COOKIES LIMIT %s OFFSET %s", limit,offset);
        try (PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cookie cookie = takeCookie(resultSet);
                cookies.add(cookie);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return cookies;
    }

    public Cookie selectById(int id) {
        Cookie cookie = null;
        String select = String.format("SELECT * FROM COOKIES WHERE ID ='%s'", id);
        try (PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cookie = takeCookie(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return cookie;
    }


    public int delete(String prediction) {
        int rows = -1;

        String select = String.format("DELETE from COOKIES WHERE COOKIE ='%s'", prediction);
        try (PreparedStatement statement = connection.prepareStatement(select)) {


            rows = statement.executeUpdate();
            System.out.println("Delete count = " + rows);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }

    public int insert(String cookie) {
        int rows = -1;
        String insert = "INSERT INTO COOKIES (COOKIE) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(insert)) {
            statement.setString(1, cookie);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }

    public int update(String prediction, String updatePrediction) {
        int rows = -1;

        String update = "UPDATE COOKIES SET COOKIE=? WHERE COOKIE=?";
        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, updatePrediction);
            statement.setString(2, prediction);

            rows = statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rows;
    }

    private Cookie takeCookie(ResultSet resultSet) throws SQLException {
        Cookie cookie = new Cookie();
        cookie.setId(resultSet.getInt("id"));
        cookie.setCookie(resultSet.getString("cookie"));
        return cookie;
    }
}
