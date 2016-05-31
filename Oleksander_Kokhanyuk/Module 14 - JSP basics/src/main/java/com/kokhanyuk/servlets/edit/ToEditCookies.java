package com.kokhanyuk.servlets.edit;

import com.kokhanyuk.servlets.login.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToEditCookies extends HttpServlet {
    private static final long serialVersionUID = 2L;

    public ToEditCookies() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//NOSONAR
        /**the method is used correctly*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            response.sendRedirect("login.jsp");//NOSONAR
            /**the method is used correctly*/
        } else {
            request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);//NOSONAR
            /**the method is used correctly*/
        }
    }
}




