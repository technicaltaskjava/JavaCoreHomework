package com.servlet;

import com.data.cookie.Cookie;
import com.data.mydao.dao.CookieDAO;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myCookieServlet")
public class MyCookieServlet extends DispetcherServlet {
    CookieDAO daoCookiy;
    private int page;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String info="";


        ServletContext servletContext = request.getServletContext();


        daoCookiy = (CookieDAO) servletContext.getAttribute("dao");
        if (request.getParameter("NEXT") != null) {

            if ((Integer) session.getAttribute("page") < (Integer) session.getAttribute("pageEnd")) {
                page = (Integer) session.getAttribute("page");
                page++;
                session.setAttribute("page", page);
            }


            super.forward("/cookieServletComands", request, response);
        } else if (request.getParameter("Previous") != null) {


            if ((Integer) session.getAttribute("page") > 1) {
                page = (Integer) session.getAttribute("page");
                page--;
            }
            session.setAttribute("page", page);
            super.forward("/cookieServletComands", request, response);


        } else if (request.getParameter("dell") != null) {
            String action = "";
            if ((request.getParameter("dellPrediction")).length() > 1) {

                int id = Integer.parseInt(request.getParameter("dellPrediction"));


                if (daoCookiy.existID(id)) {
                    System.out.println(daoCookiy.existID(id));
                    daoCookiy.delete(id);
                    action = "DELL PREDICTION SUCCESSFULLY";
                    info = "start \"" + this.getServletName() +  "\" dell   PREDICTION  (id : " + id +")";
                } else {

                    action = "NOT FOUND ID";
                }
            } else {
                action = "YOU DON'T ENTER ID";
            }
            session.setAttribute("action", action);
            super.forward("/status.jsp", request, response);

        } else if (request.getParameter("add") != null) {
            String action;
            String message = request.getParameter("addPrediction");

            if (!message.isEmpty()) {
                daoCookiy.create(new Cookie(0, message));
                action = "ADD NEW  PREDICTION SUCCESSFULLY";
                info = "start \"" + this.getServletName() +  "\" add new  PREDICTION " + message;
                page = 1;
                session.setAttribute("page", page);
            } else {
                action = "YOU DON'T ENTER NEW PREDICTION";
            }
            session.setAttribute("action", action);
            super.forward("/status.jsp", request, response);


        } else if (request.getParameter("up") != null) {
            String action = "";
            if ((request.getParameter("upID")).length() > 1) {
                int id = Integer.parseInt(request.getParameter("upID"));
                String message = request.getParameter("upPrediction");


                if (daoCookiy.existID(id) && message.length() > 1) {
                    Cookie cookie = new Cookie(id, message);
                    daoCookiy.update(cookie);
                    action = "UP PREDICTION SUCCESSFULLY";
                    info = "start \"" + this.getServletName() +  "\" update  PREDICTION " + message;
                } else if (!daoCookiy.existID(id)) {
                    action = "NOT FOUND ID";
                } else {
                    action = "YOU DON'T ENTER NEW PREDICTION";
                }
            } else {
                action = "YOU DON'T ENTER ID";
            }
            session.setAttribute("action", action);
            super.forward("/status.jsp", request, response);

        }


        request.setAttribute("info",info);


    }
}
