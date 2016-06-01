package com.epam.servlets;

import com.epam.dao.CookieDAO;
import com.epam.dao.beans.CookieBean;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by Oleg on 5/25/2016.
 */

@WebServlet(name = "EditCookie", urlPatterns = {"/EditCookie"})
public class EditCookieServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        CookieBean cookie = new CookieBean();

        try {
            cookie.setId(Long.valueOf(request.getParameter("id")));
            cookie.setCookie(CookieDAO.getCookie(connection, cookie).getCookie());

        } catch (SQLException e) {
            logger.error("Database connection problem", e);
            try {
                throw new ServletException("DB Connection problem.");
            } catch (Exception e1) {
                logger.error(e1);
            }
        }
        if (cookie.getCookie() == null) {
            response.sendRedirect("/GetCookiesPage");
            return;
        }

        request.setAttribute("cookieItem", cookie);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/editCookie.jsp");
        dispatcher.forward(request, response);

    }
}
