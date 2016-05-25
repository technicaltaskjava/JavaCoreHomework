package com.epam.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Oleg on 5/25/2016.
 */
public class CookieDAO {


    private static final int MIN = 1;
    private static final int MAX = 10;
    private static final String GET_COOKIE = "SELECT coookie FROM \"Fortune cookies\".COOKIES where COOKIE_ID = ?";
    static Logger logger = Logger.getLogger(CookieDAO.class);

    private CookieDAO(){}

    public static String getCookie(Connection connection) throws SQLException {
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
