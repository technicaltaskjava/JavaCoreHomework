package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCurrentCookie extends HttpServlet {
    public GetCurrentCookie() {
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
        getCurrentCookie(request, response);
    }

    private void getCurrentCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        request.setAttribute("cookieId", request.getParameter("cookieId"));
        request.setAttribute("cookieName", request.getParameter("cookieName"));
        request.setAttribute("active", request.getParameter("active"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EditCookie");
        dispatcher.forward(request, response);
    }
}
