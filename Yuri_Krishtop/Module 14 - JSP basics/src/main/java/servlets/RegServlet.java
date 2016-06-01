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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuriy Krishtop on 22.05.2016.
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new H2DaoFactory();
        try {
            Connection connection = (Connection) factory.getContext();
            H2UserDao dao = (H2UserDao) factory.getDao(connection, User.class);
            resp.setContentType("text/html");
            String confirmPass = req.getParameter("confirmPass");
            String login = req.getParameter("login");
            String pass = req.getParameter("pass");
            String responseMessage = "";
            String email = req.getParameter("email");
            if(!Validate.isLoginValid(login)) {
                responseMessage += "Invalid login.";
            }
            if(!Validate.isEmailValid(email)) {
                responseMessage += "Invalid email.";
            }
            if(!Validate.isPasValid(pass)) {
                responseMessage += "Invalid password.";
            }
            if(!pass.equals(confirmPass)) {
                responseMessage += "Password and confirm password are not same.";
            }
            if(dao.isLoginUsed(login)) {
                responseMessage += "Login already exists, please, enter other.";
            }
            if(dao.isEmailUsed(email)) {
                responseMessage += "Email already exists, please, enter other.";
            }
            PrintWriter out = resp.getWriter();
            int countUsersBeforeInsert = dao.getCountElements();
            if(responseMessage.length() == 0) {
                User user = new User(login, pass, email);
                dao.insert(user);
            }
            int countUsersAfterInsert = dao.getCountElements();
            if(countUsersBeforeInsert >= countUsersAfterInsert) {
                responseMessage += "Registration not finished, please try again.";
                out.println(responseMessage);
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

}
