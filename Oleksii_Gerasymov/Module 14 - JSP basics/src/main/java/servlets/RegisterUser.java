package servlets;

import jdbc.DbUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUser extends HttpServlet {
    public RegisterUser() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loginUser(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        response.setContentType("text/html");
        DbUser.addUser(username, email, password, birthdate);
        request.setAttribute("successReg", "You are successfully registered! Now you can login!");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Signin");
        dispatcher.forward(request, response);
    }
}
