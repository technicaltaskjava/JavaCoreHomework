package servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Sergey Solovyov
 */

public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies){
            c.setMaxAge(0);
            c.setValue("");
            c.setPath("/");
            resp.addCookie(c);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/classes/html/logout.html");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException|IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
}
