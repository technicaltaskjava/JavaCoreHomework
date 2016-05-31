package com.epam.servlet;

import com.epam.model.FortuneCookie;
import com.epam.facade.impl.CookieFacadeImpl;
import com.epam.facade.CookieFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Olga Kramska on 29-May-16.
 */
@WebServlet(name = "PredictionEditorServlet", urlPatterns = "/edit")
public class PredictionEditorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cookie = request.getParameter("cookie");
        String prediction = request.getParameter("prediction");
        FortuneCookie fortuneCookie = new FortuneCookie(id, cookie, prediction);
        CookieFacade facade = new CookieFacadeImpl();
        facade.updatePrediction(fortuneCookie);
    }
}
