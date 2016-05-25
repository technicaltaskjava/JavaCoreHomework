package com.epam.servlets;

import com.epam.dao.UserDAO;
import com.epam.dao.beans.UserBean;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        logger.error(request.getParameter("name"));

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        try {
            if (UserDAO.checkLogin(new UserBean(username, password), con)) {
                HttpSession session = request.getSession();
                session.setAttribute("User", username);
                response.sendRedirect("cookiesTable.html");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                PrintWriter out = response.getWriter();

                logger.error("User not found with username=" + username);

                rd.include(request, response);
                out.println("<font color=red>No user found with given username and password, try again or register first.</font>");
            }
        } catch (SQLException e) {
            logger.error("Database connection problem");
            throw new ServletException("DB Connection problem.");
        }
    }
}

