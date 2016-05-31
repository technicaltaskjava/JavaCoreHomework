package com.servlet;

import com.data.mydao.base.BaseContact;
import com.data.users.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class RegisterServlet extends HttpServlet {
    private List<User> users;
    private String message = "<!DOCTYPE html>\n" +
            "<html id=\"background\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Fortune Cookie</title>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n" +
            "    <script src=\"js/jquery-2.2.3.js\"></script>\n" +
            "    <script src=\"js/register.js\"></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<header id=\"head\">\n" +
            "    <img src=\"img/MyFortuneCookie.png\" alt=\"MyFortuneCookie\">\n" +
            "</header>\n" +
            "<div class=\"main\">\n" +
            "    <h3>REGISTRATION</h3>\n" +
            "    <p class=\"line\"></p>\n" +
            "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
            "    <span style=\"color: rgba(59, 21, 33, 0.62); font-size: 30px\">Username is already reserved. Please try again &#128276;</span>\n" +
            "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
            "    <form class=\"registerVolid\" action=\"registerServlet\" method=\"post\">\n" +
            "        <input id=\"login\" type=\"text\" placeholder=\"Input login\" name=\"loginUser\"><br>\n" +
            "        <input id=\"email\" type=\"email\" placeholder=\"Input email\" name=\"emailUser\"><br>\n" +
            "        <input id=\"password\" type=\"password\" placeholder=\"Input password\" name=\"passwordUser\"><br>\n" +
            "        <button id=\"btnEnter\" type=\"submit\" name=\"button\">Register</button>\n" +
            "        <div class=\"errors\">\n" +
            "        </div>\n" +
            "    </form>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n";


    private boolean checkUserLogin(User user, List<User>users) {
        boolean result = true;
        for (User us : users) {
            if (us.getLogin().equals(user.getLogin())) {
                result = false;
            }
        }
        return result;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        users = (List<User>)((BaseContact)servletContext.getAttribute("baseContact")).getUsers();
        String log = request.getParameter("loginUser");
        String em = request.getParameter("emailUser");
        String pwd = request.getParameter("passwordUser");
        User newUser = new User(log, pwd, em);
        if (checkUserLogin(newUser,users)) {
            ((BaseContact)servletContext.getAttribute("baseContact")).addUser(newUser);
            response.sendRedirect("startPage.html");
            String info = "start \"" + this.getServletName() +  "\" register  user " + newUser.getLogin();
            request.setAttribute("info",info);
        } else {
            response.setContentType("text/html");
            response.getWriter().println(message);
        }
    }

}
