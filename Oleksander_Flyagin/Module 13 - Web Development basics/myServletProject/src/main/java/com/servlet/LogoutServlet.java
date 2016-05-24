package com.servlet;



import com.data.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    break;
                }
            }
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("PRINCIPAL");
        if (session != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
            String info = "start \"" + this.getServletName() + "\" user " + user.getLogin() + " logout";
            request.setAttribute("info", info);
            session.invalidate();
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/logout.html");
            requestDispatcher.forward(request, response);
        }

    }


}

