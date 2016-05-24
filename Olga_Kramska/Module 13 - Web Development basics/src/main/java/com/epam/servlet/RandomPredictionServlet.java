package com.epam.servlet;

import com.epam.dao.DaoFactory;
import com.epam.dao.RepositoryDao;
import com.epam.model.FortuneCookie;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Olga Kramska on 21-May-16.
 */
@WebServlet(name = "RandomPredictionServlet", urlPatterns = "/random")
public class RandomPredictionServlet extends HttpServlet {

    @Resource(name = "jdbc/H2")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepositoryDao<FortuneCookie, Integer> cookieDao = DaoFactory.getCookieDao(dataSource);
        List<FortuneCookie> fortuneCookies = cookieDao.getAll();
        int random = (int) (Math.random() * (fortuneCookies.size()));
        PrintWriter out = response.getWriter();
        out.println(fortuneCookies.get(random).getPrediction());
    }
}