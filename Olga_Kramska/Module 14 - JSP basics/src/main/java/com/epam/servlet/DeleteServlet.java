package com.epam.servlet;

import com.epam.facade.impl.CookieFacadeImpl;
import com.epam.facade.CookieFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olga Kramska on 30-May-16.
 */
@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CookieFacade facade = new CookieFacadeImpl();
        facade.deletePrediction(id);
    }
}
