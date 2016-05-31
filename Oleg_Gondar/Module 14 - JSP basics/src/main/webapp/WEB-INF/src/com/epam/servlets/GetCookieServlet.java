package com.epam.servlets;

import com.epam.dao.CookieDAO;
import com.epam.dao.beans.CookieBean;

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
 * Created by Oleg on 5/31/2016.
 */

@WebServlet(name = "GetCookiesPage", urlPatterns = {"/GetCookiesPage"})
public class GetCookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public GetCookieServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");

        List<CookieBean> list = null;
        try {
            list = CookieDAO.getPageCookiesList(connection, (page-1)*recordsPerPage, recordsPerPage);
            int noOfRecords = CookieDAO.getNoOfRecords(connection, (page-1)*recordsPerPage, recordsPerPage);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("cookieList", list);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            RequestDispatcher view = request.getRequestDispatcher("tableOfCookies.jsp");
            view.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
