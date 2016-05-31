package servlets.actions;

import dao.DAO;
import dao.accessor.UserAccessor;
import dao.entity.User;
import dao.exception.AddUserException;
import dao.util.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/registrationAction")
public class RegistrationAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (UserAccessor userAccessor = DAO.getInstance().getUserAccessor()) {
            Cookie registered = new Cookie("Status", UserStatus.UNKNOWN.toString());

            String login = req.getParameter("login");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String firstName = req.getParameter("firstName");
            String secondName = req.getParameter("secondName");

            if (!userAccessor.isUserExist(login)) {
                User user = new User();
                user.setLogin(login);
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setSecondName(secondName);

                userAccessor.addUser(user);

                registered.setValue(UserStatus.REGISTERED.toString());
                resp.addCookie(registered);
                resp.sendRedirect("/predication");
            } else {
                resp.addCookie(registered);
                throw new AddUserException("User with such login exist");
            }
        }
    }
}
