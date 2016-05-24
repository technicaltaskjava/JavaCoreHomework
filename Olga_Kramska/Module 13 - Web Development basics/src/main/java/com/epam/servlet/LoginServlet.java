package com.epam.servlet;

import com.epam.dao.AbstractUserDao;
import com.epam.dao.DaoFactory;
import com.epam.model.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Olga Kramska on 21-May-16.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Resource(name = "jdbc/H2")
    private DataSource dataSource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AbstractUserDao<User, Integer> userDao = DaoFactory.getUserDao(dataSource);
        User user = userDao.get(username);

        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            request.getRequestDispatcher("./predictions.html").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
}
