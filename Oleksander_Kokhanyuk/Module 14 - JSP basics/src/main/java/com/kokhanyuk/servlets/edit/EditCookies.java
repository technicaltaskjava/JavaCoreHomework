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
public class EditCookies extends HttpServlet {
    private static final long serialVersionUID = 2L;
    private static Logger log = Logger.getLogger("login");

    public EditCookies() {
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
            String[] reg = requestParameter.split(":");
            int id = 0;
            try {
                id = Integer.parseInt(reg[0].trim());
                String message = reg[1].trim();
                changeCookies(id, message);
            } catch (NumberFormatException e) {
                log(e.getMessage(), e);
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print(getMessage());
            out.close();
        }
    }

    private void changeCookies(int id, String message) {

        boolean foundID = false;
        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            ResultSet rs = stmt.executeQuery("select id from cookies");//NOSONAR


            /**everything is closed properly*/
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    foundID = true;
                }
            }
            if (foundID) {
                stmt.execute("UPDATE COOKIES SET MESSAGE ='" + message + "' WHERE ID=" + id + ";");//NOSONAR
            } else {
                stmt.execute("INSERT INTO COOKIES (ID, MESSAGE, TIME, COOKED) VALUES (" + id + ", '" + message + "', CURRENT_TIME, TRUE)");
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

        Statement stmt = null;
        StringBuilder messages = new StringBuilder();
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            ResultSet rs = stmt.executeQuery("select id, message from cookies ORDER BY id");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                messages.append(rs.getInt("id") + " : " + rs.getString("message") + "<br>");
            }
            rs.close();
            stmt.close();
            log.info("Messages is update");
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
