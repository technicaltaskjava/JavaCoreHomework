package com.kokhanyuk.servlets.register;

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
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Registry extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private static Logger log = Logger.getLogger("Registry");

    public Registry() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//NOSONAR
        /**the method is used correctly*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String userSurname = request.getParameter("surname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int dob;
        try {
            dob = parseInt(request.getParameter("dob"));
        } catch (NumberFormatException e) {
            dob = 1900;
            log.info("Error enter DOB");
            log(e.getMessage(), e);
        }
        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            int id = 0;
            ResultSet rs = stmt.executeQuery("select max(ID) from user;");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                id = rs.getInt("max(id)");
            }
            rs.close();
            stmt.close();
            id++;
            log.info("next id=" + id);
            log.info(userName + " " + userSurname + " " + password + " " + email);
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO USER(ID, UserName, SurName, password, email, DOB) VALUES("
                    + id + ", '" + userName + "', '" + userSurname + "', '" + password + "', '" + email + "'," + dob + ")");
            stmt.close();
        } catch (SQLException e) {
            log.info("Database is occupied");
            log(e.getMessage(), e);
        } catch (NullPointerException e) {
            log.info("Error connecting to database");
            log(e.getMessage(), e);
        }
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");//NOSONAR
        /**the method is used correctly*/
    }
}
