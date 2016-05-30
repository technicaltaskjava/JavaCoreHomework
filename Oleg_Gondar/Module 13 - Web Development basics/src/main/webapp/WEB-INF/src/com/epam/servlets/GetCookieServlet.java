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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Oleg on 5/25/2016.
 */

@WebServlet(name = "Cookie", urlPatterns = {"/Cookie"})
public class GetCookieServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        {

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement ps = null;
            try {
                String sql = "SELECT coookie FROM \"Fortune cookies\".COOKIES where COOKIE_ID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, ThreadLocalRandom.current().nextInt(1, 11));

                ResultSet rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    RequestDispatcher rdHeader = getServletContext().getRequestDispatcher("/cookieHeader.html");
                    RequestDispatcher rdFooter = getServletContext().getRequestDispatcher("/cookieFooter.html");
                    PrintWriter out = response.getWriter();
                    rdHeader.include(request, response);
                    out.println("<h2 id=\"message\">" + rs.getString("coookie") + "</h2>");
                    rdFooter.include(request, response);
                }
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
