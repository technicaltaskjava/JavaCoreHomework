package com.data.mydao.base;

import com.data.cookie.Cookie;
import com.data.mydao.dao.CookieDAO;
import com.data.mydao.dao.CookieDAOJDBC;
import com.data.mydao.dao.UserDAO;
import com.data.mydao.dao.UserDAOJDBC;
import com.data.mydao.mypool.MyConnectionPool;
import com.data.users.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseContact {
    MyConnectionPool pool = new MyConnectionPool();

    public BaseContact() {
    }

    public List<Cookie> getCookies() {
        Cookie cookie = null;
        List<Cookie> cookies = new ArrayList();
        CookieDAO cookieDAO;
        try {
            cookieDAO = new CookieDAOJDBC(pool.retrieve());
            cookies = cookieDAO.cookieList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cookies;
    }


    public List<User> getUsers() {
        User user = null;
        List<User> users = new ArrayList();
        UserDAO userDAO;
        try {
            userDAO = new UserDAOJDBC(pool.retrieve());
            users = userDAO.usersList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public User addUser(User user) {
        UserDAO userDAO;
        try {
            userDAO = new UserDAOJDBC(pool.retrieve());
            userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
