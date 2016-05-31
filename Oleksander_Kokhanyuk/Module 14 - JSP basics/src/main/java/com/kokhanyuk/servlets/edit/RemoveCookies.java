package com.kokhanyuk.servlets.edit;

import com.kokhanyuk.servlets.login.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class RemoveCookies extends HttpServlet {
    private static final long serialVersionUID = 2L;
    private static Logger log = Logger.getLogger("login");

    public RemoveCookies() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);//NOSONAR
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);//NOSONAR
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            response.sendRedirect("login.jsp");//NOSONAR
            /**the method is used correctly*/
        } else {

            String requestParameter = request.getParameter("request");
            log.info(requestParameter);
            String reg = requestParameter;
            int id = 0;
            try {
                id = Integer.parseInt(reg.trim());
            } catch (NumberFormatException e) {
                log(e.getMessage(), e);
            }
            removeCookies(id);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print(getMessage());
            out.close();
        }
    }

    private void removeCookies(int id) {
        boolean remID = false;
        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            ResultSet rs = stmt.executeQuery("select id from cookies");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    stmt.execute("DELETE FROM cookies WHERE id=" + id);
                    remID = true;
                }
            }
            if (remID) {
                log.info("ID not found");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.info("Database is occupied");
            log(e.getMessage(), e);
        } catch (NullPointerException e) {
            log.info("Error connecting to database");
            log(e.getMessage(), e);
        }
    }

    private String getMessage() {

        StringBuilder messages = new StringBuilder();
        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            ResultSet rs = stmt.executeQuery("select id, message from cookies ORDER BY id");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                messages.append(rs.getInt("id") + " : " + rs.getString("message") + "<br>");
            }
            log.info("Messages is update");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.info("Database is occupied");
            log(e.getMessage(), e);
        } catch (NullPointerException e) {
            log.info("Error connecting to database");
            log(e.getMessage(), e);
        }
        return messages.toString();
    }

}
