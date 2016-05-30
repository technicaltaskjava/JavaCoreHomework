package javase.web.servlets;

import javase.constants.Constants;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to process logout (keeps current cookie message)
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class LogoutServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String currentMessage = (String)session.getAttribute(Constants.MESSAGE);
        session.invalidate();
        session = request.getSession(true);
        session.setAttribute(Constants.MESSAGE, currentMessage);
        response.sendRedirect("login");
    }
}