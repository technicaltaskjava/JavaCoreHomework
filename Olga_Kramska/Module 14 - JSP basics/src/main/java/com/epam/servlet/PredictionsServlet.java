package com.epam.servlet;

import com.epam.dto.PredictionDTO;
import com.epam.model.FortuneCookie;
import com.epam.model.MetaData;
import com.epam.model.User;
import com.epam.facade.impl.CookieFacadeImpl;
import com.epam.facade.CookieFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Olga Kramska on 27-May-16.
 */
@WebServlet(name = "GetPredictionsServlet", urlPatterns = "/predictions")
public class PredictionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        CookieFacade facade = new CookieFacadeImpl();
        List<PredictionDTO> predictions = facade.findPredictions(user.getId());
        request.setAttribute("predictions", predictions);
        request.getRequestDispatcher("predictions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        PredictionDTO predictionDTO;
        CookieFacade facade = new CookieFacadeImpl();
        String cookie = request.getParameter("cookie");
        String prediction = request.getParameter("prediction");
        FortuneCookie fortuneCookie;
        if (cookie != null && prediction != null) {
            fortuneCookie = new FortuneCookie(cookie, prediction);
            MetaData metaData = facade.addCookie(user, fortuneCookie);
            predictionDTO = new PredictionDTO(fortuneCookie.getId(), fortuneCookie.getName(),
                    fortuneCookie.getPrediction(), metaData.getTimeAdded());
            request.setAttribute("prediction", predictionDTO);
            request.getRequestDispatcher("addPredictionTpl.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("predictions.jsp").forward(request, response);
        }
    }
}
