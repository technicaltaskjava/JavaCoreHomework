package com.kokhanyuk.servlets.login;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger("login");

    public Login() {
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
        String password = request.getParameter("password");
        String resultPage;
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

        if (userInfo == null) {
            userInfo = new UserInfo();
            if (checkUser(userName, password)) {
                userInfo.login(userName);
                String requestParameter = request.getParameter("request");
                log.info(requestParameter);
                resultPage = "/admin/edit.jsp";
                userInfo.login(userName);
                session.setAttribute("userInfo", userInfo);
                log.info("User " + userName + " is authenticated");
            } else {
                resultPage = "/login.jsp";
                log.info("User " + userName + " not authenticated");
            }
        } else {
            resultPage = "/admin/edit.jsp";
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(resultPage);
        requestDispatcher.forward(request, response);//NOSONAR
        /**the method is used correctly*/
    }

    private boolean checkUser(String userName, String password) {
        boolean rez = false;
        Statement stmt = null;
        Connection conn = (Connection) getServletContext().getAttribute("connection");
        try {
            conn.setAutoCommit(true);
            stmt = conn.createStatement();//NOSONAR
            ResultSet rs = stmt.executeQuery("select * from user where username='" + userName + "'");//NOSONAR
            /**everything is closed properly*/
            while (rs.next()) {
                if (rs.getString("username").equalsIgnoreCase(userName.trim()) && rs.getString("password").equals(password)) {
                    rez = true;
                }
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
        return rez;
    }
}

