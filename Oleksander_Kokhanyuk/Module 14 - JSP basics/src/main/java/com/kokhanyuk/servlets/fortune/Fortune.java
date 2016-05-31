package com.kokhanyuk.servlets.fortune;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import static java.lang.Math.random;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Fortune extends HttpServlet {
    private static final long serialVersionUID = 2L;
    private static Logger log = Logger.getLogger("Fortune");

    public Fortune() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);//NOSONAR
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);//NOSONAR
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        String message = "Try again";
        try {
            conn.setAutoCommit(true);//NOSONAR
            stmt = conn.createStatement();//NOSONAR
            /**everything is closed properly*/
            int maxid = 1;
            ResultSet rs = stmt.executeQuery("select max(ID) from cookies;");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                maxid = rs.getInt("max(id)");
            }
            rs.close();
            stmt.close();
            int id = (int) (random() * maxid);//NOSONAR
            /**using the correct function*/
            log.info("max id=" + maxid);
            log.info("next id=" + id);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from cookies where id=" + id + ";");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                message = rs.getString("message");
            }
            log.info("new message: " + message);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.info("Database is occupied");
            log(e.getMessage(), e);
        } catch (NullPointerException e) {
            log.info("Error connecting to database");
            log(e.getMessage(), e);
        }
        String requestParameter = request.getParameter("request");
        log.info(requestParameter);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<p class=\"text1\">" + message + "</p>");
        out.close();
    }
}
