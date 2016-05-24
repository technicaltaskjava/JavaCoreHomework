package com.servlet;


import com.data.mydao.base.BaseContact;
import com.data.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private List<User> users;


    private boolean checkUserLogin(User user, List<User> users) {
        boolean result = false;
        for (User us : users) {
            if (us.getLogin().equals(user.getLogin()) && us.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return result;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServletContext servletContext = request.getServletContext();
        users = (List<User>)((BaseContact)(servletContext.getAttribute("baseContact"))).getUsers();
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && password != null) {
            User user = new User(username, password);
            if (checkUserLogin(user, users)) {
                String info = "start \"" + this.getServletName() +  "\"  user \'" + user.getLogin() + "\' login";
                request.setAttribute("info",info);
                HttpSession session;
                session = request.getSession();
                session.setAttribute("PRINCIPAL", user);
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", username);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                Cookie[] cookies = request.getCookies();


                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.html");
                requestDispatcher.forward(request, response);
                return;
            }
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
        PrintWriter out = response.getWriter();
        out.println("<div style=\"margin-left: 72%\">\n" +
                "    <h3 style=\"margin-left: 15%; margin-right: 10%; \">Login or password isn't correct</h3>\n" +
                "</div>");
        rd.include(request, response);
    }
}


