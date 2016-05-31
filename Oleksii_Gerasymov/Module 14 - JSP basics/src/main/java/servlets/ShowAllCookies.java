package servlets;

import jdbc.DbCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAllCookies extends HttpServlet {
    public ShowAllCookies() {
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
        showCookies(request, response);
    }

    private void showCookies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cookieList", DbCookie.getAllCookies());
        HttpSession session = request.getSession();
        int cookiesPerPage = (int) session.getAttribute("cookiesPerPage");
        int listSize = DbCookie.getAllCookies().size();
        int maxPage = listSize / cookiesPerPage;
        if (listSize % cookiesPerPage != 0 ) {
            maxPage++;
        }
        session.setAttribute("maxPage", maxPage);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Operation");
            dispatcher.forward(request, response);
    }

}
