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

@WebServlet(urlPatterns = "/login")
public class LoginPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletHelper.isUserRegistered(req.getCookies())) {
            req.getRequestDispatcher("html/registered/logout.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("html/login.html").forward(req, resp);
        }
    }
}
