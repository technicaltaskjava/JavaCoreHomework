package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/ControllerLogout"})
public class ControllerLogout extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cooky : cookies) {
                    cooky.setMaxAge(0);
                    response.addCookie(cooky);

                }

            out.print("You are successfully logged out!");
            out.close();
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index");
            dispatcher.forward(request, response);
        }

    }
}

