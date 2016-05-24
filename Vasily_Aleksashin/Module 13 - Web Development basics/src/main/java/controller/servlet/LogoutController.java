package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String AUTH = "auth";
    private static final String NAME = "name";
    private static final String JSESSIONID = "JSESSIONID";

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            Cookie[] cookies = req.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    clearCookie(resp, cookie);
                }
        }
    }

    private void clearCookie(final HttpServletResponse resp, final Cookie cookie) {
        if (cookie.getName().equals(AUTH) || cookie.getName().equals(NAME) || cookie.getName().equals(JSESSIONID)) {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
    }
}
