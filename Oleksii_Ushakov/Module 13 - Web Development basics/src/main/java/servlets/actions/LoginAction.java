package servlets.actions;

import dao.DAO;
import dao.accessor.UserAccessor;
import dao.entity.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/LoginAction")
public class LoginAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (UserAccessor userAccessor = DAO.getInstance().getUserAccessor()) {
            Cookie registered = new Cookie("Status", UserStatus.UNKNOWN.toString());

            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (userAccessor.isUserExist(login)) {
                if (password.equals(userAccessor.getUserByLogin(login).getPassword())) {
                    registered.setValue(UserStatus.REGISTERED.toString());
                    resp.addCookie(registered);
                    resp.sendRedirect("predication");
                }
            } else {
                resp.addCookie(registered);
                req.getRequestDispatcher("html/errors/NoSuchLogin.html").forward(req, resp);
            }
        }
    }
}
