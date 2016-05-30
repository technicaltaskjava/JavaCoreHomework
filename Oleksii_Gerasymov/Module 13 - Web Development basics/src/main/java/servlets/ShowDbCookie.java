package servlets;

import jdbc.DbCookie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowDbCookie extends HttpServlet {

    public ShowDbCookie() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showCookie(request, response);
    }

    private void showCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String currentCookieText = "Your cookie is:<br><p class=\"currentCookie\" align=\"center\">";
        currentCookieText += DbCookie.getRandomCookie() + "</p>";
        request.setAttribute("currentCookie", currentCookieText);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }
}
