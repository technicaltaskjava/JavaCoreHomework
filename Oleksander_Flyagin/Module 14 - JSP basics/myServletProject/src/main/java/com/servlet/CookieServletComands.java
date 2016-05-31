package com.servlet;


import com.data.cookie.Cookie;
import com.data.mydao.dao.CookieDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cookieServletComands")
public class CookieServletComands extends HttpServlet {
    private final static int LIMIT = 5;
    private int page = 1;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cookie> cookiysBeen = new ArrayList();
        List<Cookie> cookies = new ArrayList();
        HttpSession session = request.getSession();

        ServletContext servletContext = request.getServletContext();
        cookies = ((CookieDAO) servletContext.getAttribute("dao")).cookieList();


        if (session.getAttribute("page") != null && (Integer) session.getAttribute("page") >= 1) {

            page = (Integer) session.getAttribute("page");
        } else {
            session.setAttribute("page", page);

        }

        int countPage = Math.abs(cookies.size() / LIMIT);

        session.setAttribute("pageEnd", countPage);
        int start = ((page - 1) * LIMIT);
        int end = start + LIMIT;


        while (start < end) {
            cookiysBeen.add(cookies.get(start));
            start++;
        }


        request.setAttribute("table", cookiysBeen);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myCookiey.jsp");
        dispatcher.forward(request, response);


    }
}
