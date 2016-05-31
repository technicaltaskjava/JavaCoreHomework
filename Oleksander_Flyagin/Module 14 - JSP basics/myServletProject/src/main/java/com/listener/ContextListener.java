package com.listener;



import com.data.cookie.Cookie;
import com.data.mydao.dao.CookieDAO;
import com.data.mydao.dao.CookieDAOJDBC;
import com.data.mydao.mypool.MyConnectionPool;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        MyConnectionPool pool = new MyConnectionPool();
        ServletContext servletContext = event.getServletContext();
        CookieDAO dao = null;
        List<Cookie> listCookies = new ArrayList();;

        try {
            Class.forName("org.h2.Driver");
            dao = new CookieDAOJDBC(pool.retrieve());
            listCookies = dao.cookieList();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("dao",dao);
        servletContext.setAttribute("listCookies", listCookies);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
