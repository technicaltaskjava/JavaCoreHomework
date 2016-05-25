package com.epam.dao;

import com.epam.dao.beans.CookieBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Oleg on 5/25/2016.
 */
public class CookieDAO {

    private static final String GET_COOKIE = "SELECT coookie FROM \"Fortune cookies\".COOKIES where COOKIE_ID = ?";
    private CookieBean cookieBean;

    public CookieDAO(CookieBean cookieBean) {
        this.cookieBean = cookieBean;
    }

    public static String getCookie(Connection connection) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_COOKIE);
            ps.setInt(1, ThreadLocalRandom.current().nextInt(1, 11));
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return rs.getString("coookie");
            }
        } catch (SQLException e) {
            throw e;
        }
        return "";
    }
}
