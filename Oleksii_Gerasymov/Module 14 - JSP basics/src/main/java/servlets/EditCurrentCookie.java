package servlets;

import jdbc.DbCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCurrentCookie extends HttpServlet {
    public EditCurrentCookie() {
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
        editCookie(request, response);
    }

    private void editCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        DbCookie.editCookie(Integer.valueOf(request.getParameter("cookieId")), request.getParameter("cookieName"),
                request.getParameter("active"));

        request.setAttribute("editMessage", "Cookie has been changed!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCookies");
        dispatcher.forward(request, response);
    }
}
