package servlets;

import jdbc.DbCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteCookie extends HttpServlet {
    public DeleteCookie() {
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
        deleteCookie(request, response);
    }

    private void deleteCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        DbCookie.deleteCookie(Integer.parseInt(request.getParameter("cookieId")),
                (int) session.getAttribute("logedId"));

        request.setAttribute("editMessage", "Cookie deleted!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCookies");
        dispatcher.forward(request, response);

    }
}
