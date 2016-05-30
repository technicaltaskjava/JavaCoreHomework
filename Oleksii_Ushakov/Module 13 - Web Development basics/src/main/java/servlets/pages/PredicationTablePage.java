package servlets.pages;

import dao.DAO;
import dao.entity.FortuneCookie;
import org.apache.log4j.Logger;
import servlets.actions.RegistrationAction;
import servlets.helpers.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/predication_table")
public class PredicationTablePage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegistrationAction.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if (!ServletHelper.isUserRegistered(req.getCookies())) {
            req.getRequestDispatcher("html/login.html").forward(req, resp);
        }

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(ServletHelper.getHTMLPageHeader());
            writer.println(ServletHelper.getHTMLPageRegisteredMenu());

            writer.println("<table id=\"predictionTable\">");
            try {
                List<FortuneCookie> fortuneCookieList = DAO.getInstance().getCookiesAccessor().getCookieList();
                for (FortuneCookie cookie : fortuneCookieList) {
                    writer.println(writeInRow(cookie.getPredication()));
                }
            } catch (Exception e) {
                logger.error(e);
                writer.print(writeInRow(e.getMessage()));
            }
            writer.println("</table>");
            writer.println(ServletHelper.getHTMLPageFooter());
        }
    }

    private String writeInRow(String message) {
        String tab = "    ";
        StringBuilder row = new StringBuilder(tab);
        row.append("<tr>")
           .append("\n")
           .append(tab)
           .append(tab)
           .append("<th>")
           .append(message)
           .append("</th>")
           .append("\n")
           .append(tab)
           .append("</tr>");

        return row.toString();
    }
}
