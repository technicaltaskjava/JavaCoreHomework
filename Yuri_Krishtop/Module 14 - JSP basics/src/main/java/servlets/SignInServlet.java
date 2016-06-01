package servlets;

import dao.DaoFactory;
import dao.H2DaoFactory;
import dao.H2UserDao;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuriy Krishtop on 23.05.2016.
 */

@WebServlet("/signIn")
public class SignInServlet  extends HttpServlet {

    private static final Logger log = Logger.getLogger(CookieServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new H2DaoFactory();
        try {
            Connection connection = (Connection) factory.getContext();
            H2UserDao dao = (H2UserDao) factory.getDao(connection, User.class);
            resp.setContentType("text/html");
            String login = req.getParameter("login");
            String pass = req.getParameter("pass");
            String responseMessage = "";
            if(!Validate.isLoginValid(login)) {
                responseMessage += "Invalid login.";
            }
            if(!Validate.isPasValid(pass)) {
                responseMessage += "Invalid password.";
            }
            if(dao.isLoginUsed(login)) {
                User user = dao.getUserByLogin(login);
                if(user.getPass().equals(pass)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("userId", user.getId());
                    session.setMaxInactiveInterval(300);
                } else {
                    responseMessage += "Authorization error.";
                }
            } else {
                responseMessage += "This account does not exist.";
            }
            PrintWriter out = resp.getWriter();
            out.println(responseMessage);
        } catch (SQLException e) {
            log.error(e);
        }
    }

}
