package controller;

import dao.ConnectionFactory;
import dao.Factory;
import dao.UserDao;
import dao.substance.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet(urlPatterns = {"/register"})
public class ControllerRegister extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ControllerRegister.class);
    private static final long serialVersionUID = 1L;

    public static final String URL = "jdbc:h2:D:\\homeworks\\Module-13-Web-Development-basics\\test";
    public static final String USER = "sa";
    public static final String PASSWORD = "";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection;
        Factory factory = new ConnectionFactory(URL, USER, PASSWORD);
        try {
            connection = factory.getConnection();
            UserDao manager = factory.getUserDao(connection);

            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String email = request.getParameter("email");
            String yearBirth = request.getParameter("yearBirth");
            String password = request.getParameter("pass");

            User user = new User();
            user.setLastName(lastName);
            user.setFirtsName(firstName);
            user.setEmail(email);
            user.setYearBirth(yearBirth);
            user.setPassword(password);
            int rows = manager.insert(user);

            factory.closePool();
            if (rows > 0) {
                log.info("register " + rows);
                RequestDispatcher dispatcher = request.getRequestDispatcher("site/index.html");
                dispatcher.forward(request, response);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
