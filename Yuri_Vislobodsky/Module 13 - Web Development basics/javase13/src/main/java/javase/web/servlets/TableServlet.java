package javase.web.servlets;

import javase.connectionpool.ConnectionPoolException;
import javase.dao.datasource.CookiesDAO;
import javase.dao.factory.DAOFactory;
import javase.constants.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet to show and process Table page
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class TableServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(TableServlet.class);

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String logged = (String) session.getAttribute(Constants.LOGGED);

        if (logged == null || !Constants.SUCCESSFUL.equals(logged)) {
            response.sendRedirect("login");
            return;
        }

        String userName = (String) session.getAttribute("username");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head> <meta charset=\"UTF-8\">");
        out.println("<title>MyFortuneCookie Cookies Table</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>");
        out.println("<script src=\"js/jquery-2.2.3.js\"></script>");
        out.println("<script src=\"js/table.js\"></script>");
        out.println("</head><body>");
        out.println("<div class=\"img-container\">");
        out.println("<img src=\"images/logo.png\" alt=\"MyFortuneCookie\"></div>");
        out.println("<div class=\"user-message\"></div>");
        out.print("<div class=\"user-authorize\">");
        if (userName != null) {
            out.print(String.format("Welcome, %s", userName));
        }
        out.print("&nbsp;&nbsp;|&nbsp;&nbsp;");
        out.print("<a href=\"logout\">Logout</a>");
        out.println("</div>");
        out.println("<div class=\"table-div\"> <table class=\"table-cookies\">");
        out.println("<tr><th><button class=\"button-add\"></button></th>");
        out.println("<th>Cookie name</th><th>Message</th></tr>");
        printTable(out);
        out.println("</table></div></div>");
        out.println("<div class=\"copyright\">&copy; Vislobodsky Yury, 2016</div></body></html>");
    }

    private void printTable(PrintWriter out) {
        DAOFactory daoFactory = null;
        Connection connection = null;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = daoFactory.getConnection();
            CookiesDAO cookies = daoFactory.getCookiesDAO(connection);
            ResultSet rs = cookies.selectAll();
            while (rs.next()) {
                out.println("<tr><td><button class=\"button-remove\"></button></td>");
                out.println(String.format("<td>%s</td>", rs.getString(2)));
                out.println(String.format("<td>%s</td>", rs.getString(3)));
                out.println("</tr>");
            }
            cookies.closeResultSet();
        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Exception in selecting table", e);
        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.freeConnection(connection);
                }
            } catch (ConnectionPoolException e1) {
                logger.error("ConnectionPoolException in selecting table", e1);
            }
        }
    }
}