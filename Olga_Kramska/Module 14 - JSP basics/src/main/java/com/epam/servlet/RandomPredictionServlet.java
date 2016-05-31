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
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Olga Kramska on 21-May-16.
 */
@WebServlet(name = "RandomPredictionServlet", urlPatterns = "/random")
public class RandomPredictionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieFacade facade = new CookieFacadeImpl();
        List<FortuneCookie> fortuneCookies = facade.getAllPredictions();
        int random = (int) (Math.random() * (fortuneCookies.size()));
        PrintWriter out = response.getWriter();
        out.println(fortuneCookies.get(random).getPrediction());
    }
}