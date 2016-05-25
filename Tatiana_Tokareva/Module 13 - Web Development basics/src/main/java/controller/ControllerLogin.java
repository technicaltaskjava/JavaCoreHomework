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
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet(urlPatterns = {"/login"})
public class ControllerLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ControllerLogin.class);
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = manager.selectEmail(email);
            factory.closePool();
            if (user.getPassword().equals(password)) {

                HttpSession session = request.getSession();

                RequestDispatcher dispatcher = request.getRequestDispatcher("site/index.html");
                dispatcher.forward(request, response);

            } else {

                PrintWriter out = response.getWriter();
                out.print("you are not login! please register");

            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
