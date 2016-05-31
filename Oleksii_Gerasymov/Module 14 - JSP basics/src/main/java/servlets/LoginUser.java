package servlets;

import jdbc.DbCookie;
import jdbc.DbUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUser extends HttpServlet {
    public LoginUser() {
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
        boolean checked = true;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        if (!DbUser.checkUser(username)) {
            request.setAttribute("loginError", "You are not registered!");
            checked = false;
        }
        if ((checked) && (!DbUser.checkPassword(username, password))) {
            request.setAttribute("passwordError", "Incorrect Password!");
            checked = false;
        }
        if (checked) {
            HttpSession session = request.getSession();
            session.setAttribute("authorized", true);
            session.setAttribute("viewPage", 1);
            session.setAttribute("cookiesPerPage", 5);
            int cookiesPerPage = (int) session.getAttribute("cookiesPerPage");
            int listSize = DbCookie.getAllCookies().size();
            int maxPage = listSize / cookiesPerPage;
            if (listSize % cookiesPerPage != 0 ) {
                maxPage++;
            }
            session.setAttribute("maxPage", maxPage);
            session.setAttribute("logedId", DbUser.getUserId(username));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCookies");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Signin");
            dispatcher.forward(request, response);
        }
    }
}
