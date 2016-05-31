package servlets;

import jdbc.DbCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddNewCookie extends HttpServlet {
    public AddNewCookie() {
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
        addNewCookie(request, response);
    }

    private void addNewCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        DbCookie.addCookie(request.getParameter("cookie"),request.getParameter("active"),
                (int) session.getAttribute("logedId"));
        request.setAttribute("editMessage", "Cookie added!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCookies");
        dispatcher.forward(request, response);
    }
}
