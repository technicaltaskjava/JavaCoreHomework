package javase.web.servlets;

import javase.connectionpool.ConnectionPoolException;
import javase.dao.datasource.UsersDAO;
import javase.dao.factory.DAOFactory;
import javase.dao.transfer.User;
import javase.constants.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet to show and process login page
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String registered = (String)session.getAttribute(Constants.REGISTERED);
        session.removeAttribute(Constants.REGISTERED);

        if (email != null) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            if (isSuccessLogged(user)) {
                session.setAttribute("username", user.getUserName());
                session.setAttribute(Constants.LOGGED, Constants.SUCCESSFUL);
                response.sendRedirect("table");
                return;
            }
            session.setAttribute(Constants.LOGGED, Constants.FAILED);
        }

        String logged = (String) session.getAttribute(Constants.LOGGED);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head> <meta charset=\"UTF-8\">");
        out.println("<title>MyFortuneCookie Login</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>");
        out.println("<script src=\"js/jquery-2.2.3.js\"></script>");
        out.println("<script src=\"js/common.js\"></script>");
        out.println("<script src=\"js/login.js\"></script>");
        out.println("</head><body>");
        out.println("<div class=\"img-container\">");
        out.println("<img src=\"images/logo.png\" alt=\"MyFortuneCookie\"></div>");
        out.print("<div class=\"user-message\">");
        if (registered != null && Constants.SUCCESSFUL.equals(registered)) {
            out.print("<span class=\"success\">Registration successful. Please, log in</span>");
        } else if (email != null && logged != null && !"success".equals(logged)) {
            out.print("Incorrect e-mail or password!");
        }
        out.println("</div>");
        out.println("<div class=\"user-authorize\"><a href=\"main\">Back to Main Page</a></div>");
        out.println("<div class=\"dlg-form dlg-type\">");
        out.println("<div class=\"header\">Login</div>");
        out.println("<form class=\"login-form\" action=\"login\" method=\"post\">");
        out.print("<input id=\"email\" name=\"email\" type=\"text\" placeholder=\"E-mail\" value=\"");
        if (email != null) {
            out.print(email);
        }
        out.println("\">");
        out.println("<input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Password\">");
        out.println("<button type=\"submit\" class=\"submit-btn btn-right\">Sign in</button>");
        out.println("<div class=\"sign-up\">");
        out.println("<a href=\"register\">Register</a>");
        out.println("</div></form>");
        out.println("</div><div class=\"copyright\">&copy; Vislobodsky Yury, 2016</div></body></html>");
    }

    private boolean isSuccessLogged(User user) {
        boolean success = false;
        DAOFactory daoFactory = null;
        Connection connection = null;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = daoFactory.getConnection();
            connection.setAutoCommit(false);
            UsersDAO users = daoFactory.getUsersDAO(connection);
            User userFound = users.getUser(user.getEmail(), user.getPassword());
            if (userFound != null) {
                user.setUserName(userFound.getUserName());
                user.setYearOfBirth(userFound.getYearOfBirth());
                success = true;
            }
            connection.commit();
        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Exception in checking logged user commit", e);
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                logger.error("SQLException in checking logged user rollback", e1);
            }
            try {
                if (daoFactory != null) {
                    daoFactory.freeConnection(connection);
                }
            } catch (ConnectionPoolException e1) {
                logger.error("ConnectionPoolException in checking logged user", e1);
            }
        }
        return success;
    }

}