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
@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Resource(name = "jdbc/H2")
    private DataSource dataSource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        AbstractUserDao<User, Integer> userDao = DaoFactory.getUserDao(dataSource);
        if (userDao.get(username) == null && password1.equals(password2)) {
            User user = new User(username, email, password1);
            userDao.add(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
        }
        request.getRequestDispatcher("./predictions.html").forward(request, response);
    }
}
