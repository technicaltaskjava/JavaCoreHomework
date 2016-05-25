package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/ControllerLogout"})
public class ControllerLogout extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out=response.getWriter();


       HttpSession session=request.getSession(false);

        if(session!=null) {
            session.invalidate();
        out.print("You are successfully logged out!");
        out.close();
        }else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher( "/index");
            dispatcher.forward(request, response);
        }

    }
}

