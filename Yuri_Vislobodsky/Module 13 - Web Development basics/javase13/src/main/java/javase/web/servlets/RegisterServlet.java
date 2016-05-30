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
 * Servlet to show and process Register page
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class RegisterServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String registered = (String)session.getAttribute(Constants.REGISTERED);

        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String year = request.getParameter("year");
        String password = request.getParameter("password");

        if (email != null) {
            User user = new User();
            user.setUserName(userName);
            user.setEmail(email);
            user.setYearOfBirth(Integer.valueOf(year));
            user.setPassword(password);
            if (isSuccessRegistered(user)) {
                session.setAttribute(Constants.REGISTERED, Constants.SUCCESSFUL);
                response.sendRedirect("login");
                return;
            }
        }

        session.setAttribute(Constants.REGISTERED, Constants.FAILED);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head> <meta charset=\"UTF-8\">");
        out.println("<title>MyFortuneCookie Register</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>");
        out.println("<script src=\"js/jquery-2.2.3.js\"></script>");
        out.println("<script src=\"js/common.js\"></script>");
        out.println("<script src=\"js/register.js\"></script>");
        out.println("</head><body>");
        out.println("<div class=\"img-container\">");
        out.println("<img src=\"images/logo.png\" alt=\"MyFortuneCookie\"></div>");
        out.print("<div class=\"user-message\">");
        if (email != null && registered != null && Constants.FAILED.equals(registered)) {
            out.print("Registration failed: user already exists!");
        }
        out.println("</div>");
        out.println("<div class=\"user-authorize\"><a href=\"main\">Back to Main Page</a></div>");
        out.println("<div class=\"dlg-form dlg-type\">");
        out.println("<div class=\"header\">Register</div>");
        out.println("<form class=\"register-form\" action=\"register\" method=\"post\">");
        out.print("<input id=\"username\" name=\"username\" type=\"text\" placeholder=\"Your name\" value=\"");
        if (userName != null) {
            out.print(userName);
        }
        out.println("\">");
        out.print("<input id=\"email\" name=\"email\" type=\"text\" placeholder=\"E-mail\" value=\"");
        if (email != null) {
            out.print(email);
        }
        out.println("\">");
        out.print("<input id=\"year\" name=\"year\" type=\"text\" placeholder=\"Year of birth\" value=\"");
        if (year != null) {
            out.print(year);
        }
        out.println("\">");
        out.println("<input id=\"password\" name=\"password\" type=\"password\" placeholder=\"Password\">");
        out.println("<input id=\"password2\" name=\"password2\" type=\"password\" placeholder=\"Confirm password\">");
        out.println("<button type=\"submit\" class=\"submit-btn\">Sign up</button>");
        out.println("</form>");
        out.println("</div><div class=\"copyright\">&copy; Vislobodsky Yury, 2016</div></body></html>");
    }

    private boolean isSuccessRegistered(User user) {
        boolean success = false;
        DAOFactory daoFactory = null;
        Connection connection = null;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = daoFactory.getConnection();
            connection.setAutoCommit(false);
            UsersDAO users = daoFactory.getUsersDAO(connection);
            User userFound = users.getUser(user.getEmail());
            if (userFound == null) {
                users.insert(user);
                success = true;
            }
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Exception when commit user", e);
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.error("Exception when rollback user", ex);
            }
            try {
                if (daoFactory != null) {
                    daoFactory.freeConnection(connection);
                }
            } catch (ConnectionPoolException ex) {
                logger.error("Exception when free connection", ex);
            }
        }
        return success;
    }
}