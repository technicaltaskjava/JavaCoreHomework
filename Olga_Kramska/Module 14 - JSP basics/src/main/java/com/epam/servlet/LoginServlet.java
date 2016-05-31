package com.epam.servlet;

import com.epam.model.User;
import com.epam.facade.impl.CookieFacadeImpl;
import com.epam.facade.CookieFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Olga Kramska on 21-May-16.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CookieFacade facade = new CookieFacadeImpl();
        User user = facade.findUser(username);

        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            response.sendRedirect("predictions");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
