package javase.web.servlets;

import javase.connectionpool.ConnectionPoolException;
import javase.constants.Constants;
import javase.dao.datasource.CookiesDAO;
import javase.dao.factory.DAOFactory;

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
 * Servlet to show and process Main Page
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class MainServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String sessionMessage = (String)session.getAttribute(Constants.MESSAGE);
        String hiddenMessage = request.getParameter(Constants.MESSAGE);
        String currentMessage;
        if (sessionMessage == null || (hiddenMessage != null && hiddenMessage.equals(sessionMessage))) {
            currentMessage = getRandomCookieMessage();
            session.setAttribute(Constants.MESSAGE, currentMessage);
        } else {
            currentMessage = sessionMessage;
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head> <meta charset=\"UTF-8\">");
        out.println("<title>MyFortuneCookie Main Page</title>");
        out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>");
        out.println("<script src=\"js/jquery-2.2.3.js\"></script>");
        out.println("</head><body>");
        out.println("<div class=\"img-container\">");
        out.println("<img src=\"images/logo.png\" alt=\"MyFortuneCookie\"></div>");
        out.println("<div class=\"user-message\"></div>");
        out.println("<div class=\"user-authorize\">");
        out.println("<a href=\"login\">Login</a>");
        out.println("&nbsp;&nbsp;|&nbsp;&nbsp;");
        out.println("<a href=\"register\">Register</a>");
        out.println("</div>");
        out.println("<div class=\"dlg-form open-up-type\">");
        out.println("<div class=\"header\">Virtual Fortune Cookie</div>");
        String buttonName = "Open my cookie";
        if (sessionMessage == null) {
            out.println("<div class=\"caption\">To open up your virtual fortune cookie, simply click the button below:</div>");
        } else {
            out.println(String.format("<div class=\"cookie-text\">%s</div>", currentMessage));
            buttonName = "Open another cookie";
        }
        out.println("<form class=\"open-up-form\" action=\"main\" method=\"post\">");
        out.println(String.format("<input type=\"hidden\" name=\"%s\" value=\"%s\">", Constants.MESSAGE, currentMessage));
        out.println(String.format("<button type=\"submit\" class=\"submit-btn\">%s</button>", buttonName));
        out.println("</form>");
        out.println("</div><div class=\"copyright\">&copy; Vislobodsky Yury, 2016</div> </body> </html>");
    }

    private String getRandomCookieMessage() {
        String cookieMessage = "";
        DAOFactory daoFactory = null;
        Connection connection = null;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = daoFactory.getConnection();
            CookiesDAO cookies = daoFactory.getCookiesDAO(connection);
            ResultSet rs = cookies.getRandomRecord();
            while (rs.next()) {
                cookieMessage = rs.getString(3);
            }
            cookies.closeResultSet();
        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Exception in getting random cookie", e);
        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.freeConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                logger.error("ConnectionPoolException in getting random cookie", e);
            }
        }
        return cookieMessage;
    }

}