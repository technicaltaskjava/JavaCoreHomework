package com.epam.servlets;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Oleg on 5/24/2016.
 */


@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");


        {

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            try {
                String sql = "INSERT INTO \"Fortune cookies\".USERS (USEREMAIL, USERNAME, USERPASSWORD,)\n" +
                        "VALUES \n" +
                        "(?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);

                ps.execute();

                logger.info("User registered with email=" + email);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=green>Registration successful, please login below.</font>");
                rd.include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Database connection problem");
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error("SQLException in closing PreparedStatement");
                }
            }
        }

    }

}