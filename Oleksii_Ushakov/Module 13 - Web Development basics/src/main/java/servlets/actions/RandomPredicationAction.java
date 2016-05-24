package servlets.actions;

import dao.entity.PredicationList;
import servlets.helpers.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/RandomPredicationAction")
public class RandomPredicationAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(ServletHelper.getHTMLPageHeader());

            if (ServletHelper.isUserRegistered(req.getCookies())) {
                writer.println(ServletHelper.getHTMLPageRegisteredMenu());
            } else {
                writer.println(ServletHelper.getHTMLPageNotRegisteredMenu());
            }

            writer.println("<span id=\"predictionText\">");
            writer.println(PredicationList.getRandomPredication());
            writer.println("</span>");

            writer.println("<form id=\"predictionForm\" method=\"post\" action='RandomPredicationAction'>");
            writer.println("<input id=\"predictionNext\" type=\"submit\" value=\"New prediction\">");
            writer.println("</form>");



            writer.println(ServletHelper.getHTMLPageFooter());
        }
    }
}
