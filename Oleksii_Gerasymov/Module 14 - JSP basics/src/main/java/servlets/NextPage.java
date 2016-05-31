package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NextPage extends HttpServlet {
    public NextPage() {
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
        nextPage(request, response);
    }

    private void nextPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        int currentPage = (int) session.getAttribute("viewPage");
        int maxPage = (int) session.getAttribute("maxPage");
        if (currentPage < maxPage) {
            session.setAttribute("viewPage", currentPage + 1);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCookies");
        dispatcher.forward(request, response);
    }
}
