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
@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        CookieFacade facade = new CookieFacadeImpl();
        if (facade.findUser(username) == null && password1.equals(password2)) {
            User user = new User(username, email, password1);
            facade.addUser(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
        }
        request.getRequestDispatcher("./predictions.jsp").forward(request, response);
    }
}
