package servlets.pages;

import servlets.helpers.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = {"/predication"})
public class PredicationPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletHelper.isUserRegistered(req.getCookies())) {
            req.getRequestDispatcher("html/registered/predication.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("html/predication.html").forward(req, resp);
        }
    }
}
