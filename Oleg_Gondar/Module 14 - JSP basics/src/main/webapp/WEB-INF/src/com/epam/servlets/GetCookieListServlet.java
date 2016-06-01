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
import java.util.List;


/**
 * Created by Oleg on 5/25/2016.
 */

@WebServlet(name = "GetCookies", urlPatterns = {"/GetCookies"})
public class GetCookieListServlet extends HttpServlet {


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

        List<CookieBean> cookieBeanList = null;
        try {
            cookieBeanList = CookieDAO.getCookiesList(connection);
            request.setAttribute("cookieList", cookieBeanList);
                request.setAttribute("id", request.getParameter("err"));

            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/tableOfCookies.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            logger.error("Database connection problem", e);
            try {
                throw new ServletException("DB Connection problem.");
            } catch (Exception e1) {
                logger.error(e1);
            }
        }
    }
}
