package com.epam.servlets;

import com.epam.dao.CookieDAO;
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
import java.sql.SQLException;


/**
 * Created by Oleg on 5/25/2016.
 */

@WebServlet(name = "Cookie", urlPatterns = {"/Cookie"})
public class CookieServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            try {
                RequestDispatcher rdHeader = getServletContext().getRequestDispatcher("/cookieHeader.html");
                RequestDispatcher rdFooter = getServletContext().getRequestDispatcher("/cookieFooter.html");
                PrintWriter out = response.getWriter();
                rdHeader.include(request, response);
                out.println("<h2 id=\"message\">" + CookieDAO.getCookie(connection) + "</h2>");
                rdFooter.include(request, response);
            } catch (SQLException e) {
                logger.error("Database connection problem", e);
                try {
                    throw new ServletException("DB Connection problem.");
                }catch (Exception e1){
                    logger.error(e1);
                }
            }
        }

}
