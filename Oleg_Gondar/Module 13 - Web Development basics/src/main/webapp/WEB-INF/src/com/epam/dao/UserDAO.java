package com.epam.dao;

import com.epam.dao.beans.UserBean;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Oleg on 5/25/2016.
 */
public class UserDAO {

    private static final String CHECK_USER_LOGIN = "select username from \"Fortune cookies\".USERS  where username=? and userpassword=?";
    private static final String CHECK_USERNAME = "select username from \"Fortune cookies\".USERS  where username=?";
    private static final String CREATE_USER = "INSERT INTO \"Fortune cookies\".USERS (USEREMAIL, USERNAME, USERPASSWORD,) VALUES (?, ?, ?)";
    static Logger logger = Logger.getLogger(UserDAO.class);

    private UserDAO() {
    }

    public static boolean createUser(UserBean userBean, Connection connection) throws SQLException {

        if (checkName(userBean, connection)) {
            try {
                PreparedStatement ps = connection.prepareStatement(CREATE_USER);
                ps.setString(1, userBean.getEmail());
                ps.setString(2, userBean.getUserName());
                ps.setString(3, userBean.getPassword());
                ps.execute();
                ps.close();
                return true;
            } catch (SQLException e) {
                logger.error(e);
                throw e;
            }
        }
        return false;
    }

    public static boolean checkLogin(UserBean userBean, Connection connection) throws SQLException {
        boolean result = false;
        try {
            PreparedStatement ps = connection.prepareStatement(CHECK_USER_LOGIN);
            ps.setString(1, userBean.getUserName());
            ps.setString(2, userBean.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                result = true;
            }
            ps.close();
            return result;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public static boolean checkName(UserBean userBean, Connection connection) throws SQLException {
        boolean result = true;
        try {
            PreparedStatement ps = connection.prepareStatement(CHECK_USERNAME);
            ps.setString(1, userBean.getUserName());
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                result = false;
            }
            ps.close();
            return result;
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }


}
