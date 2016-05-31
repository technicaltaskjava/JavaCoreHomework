package com.epam.dao;

import com.epam.dao.beans.CookieBean;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Oleg on 5/25/2016.
 */
public class CookieDAO {


    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final String GET_COOKIE = "SELECT coookie FROM \"Fortune cookies\".COOKIES where COOKIE_ID = ?";
    private static final String GET_ALL_COOKIES = "SELECT cookie_id, coookie FROM \"Fortune cookies\".COOKIES";
    private static final String UPDATE_COOKIE = "UPDATE \"Fortune cookies\".COOKIES SET COOOKIE = ? WHERE COOKIE_ID = ?";
    private static final String INSERT_COOKIE = "INSERT INTO \"Fortune cookies\".COOKIES (COOKIE_ID, COOOKIE)\n" +
            "VALUES \n" +
            "(?, ?)";
    private static final String DELETE_COOKIE = "delete from \"Fortune cookies\".COOKIES where COOKIE_ID = ?";
    static Logger logger = Logger.getLogger(CookieDAO.class);

    private CookieDAO() {
    }

    public static List<CookieBean> getPageCookiesList(Connection connection, int offset, int noOfRecords) throws SQLException {
        String query = "SELECT cookie_id, coookie FROM \"Fortune cookies\".COOKIES limit " + offset + ", " + noOfRecords;
        List<CookieBean> cookies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                cookies.add(new CookieBean(resultSet.getLong(1), resultSet.getString(2)));
            }
            resultSet.close();
            statement.close();
            resultSet.close();
            return cookies;
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static int getNoOfRecords(Connection connection, int offset, int noOfRecords) throws SQLException{
        int records = 1;
        String query = "SELECT cookie_id, coookie FROM \"Fortune cookies\".COOKIES";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            records++;
        }
        resultSet.close();
        statement.close();
        return records;
    }

    public static List<CookieBean> getCookiesList(Connection connection) throws SQLException {
        List<CookieBean> cookies = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COOKIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cookies.add(new CookieBean(resultSet.getLong(1), resultSet.getString(2)));
            }
            resultSet.close();
            preparedStatement.close();
            return cookies;
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static void insertCookie(Connection connection, CookieBean cookie) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COOKIE);
            preparedStatement.setString(2, cookie.getCookie());
            preparedStatement.setLong(1, cookie.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static void deleteCookie(Connection connection, CookieBean cookie) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COOKIE);
            preparedStatement.setLong(1, cookie.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static CookieBean getCookie(Connection connection, CookieBean cookieBean) throws SQLException {
        CookieBean cookie = new CookieBean();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_COOKIE);
            ps.setLong(1, cookieBean.getId());
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                cookie.setCookie(rs.getString("coookie"));
                cookie.setId(cookieBean.getId());
            }
            ps.close();
            return cookie;
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static String getRandomCookie(Connection connection) throws SQLException {
        String cookie = "";
        try {
            PreparedStatement ps = connection.prepareStatement(GET_COOKIE);
            ps.setInt(1, ThreadLocalRandom.current().nextInt(MIN, MAX));
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                cookie = rs.getString("coookie");
            }
            ps.close();
            return cookie;
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }
}
