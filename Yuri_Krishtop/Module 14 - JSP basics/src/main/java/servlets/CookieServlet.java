package servlets;

import dao.DaoFactory;
import dao.GenericDao;
import dao.H2CookieDao;
import dao.H2DaoFactory;
import entities.Cookie;
import entities.PredictionsBean;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Yuriy Krishtop on 19.05.2016.
 */
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CookieServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new H2DaoFactory();
        try {
            Connection connection = (Connection) factory.getContext();
            GenericDao dao = factory.getDao(connection, Cookie.class);
            resp.setContentType("text/html");
            if(req.getParameter("showPrediction") != null){
                PrintWriter out = resp.getWriter();
                Random r = new Random();
                int index = r.nextInt(dao.getCountElements()) + 1;
                Cookie cookie = (Cookie) dao.getById(index);
                out.println(cookie.getPrediction());
            }
            if(req.getParameter("tablePredictions") != null){
                updatePredictionTable(dao, req);
            }
            if(req.getParameter("insertPrediction") != null){
                String[] newPredictions = req.getParameterValues("insertPrediction");
                Cookie cookie = new Cookie();
                cookie.setPrediction(newPredictions[0]);
                dao.insert(cookie);
                H2CookieDao h2CookieDao = (H2CookieDao) dao;
                HttpSession session = req.getSession();
                session.setAttribute("idLastCookie", h2CookieDao.getIdLastCookie());
                updatePredictionTable(dao, req);
            }
            if(req.getParameter("deletePrediction") != null){
                int id = getCookieId("deletePrediction", req, dao);
                dao.deleteById(id);
                updatePredictionTable(dao, req);
            }
            if(req.getParameter("editPrediction") != null){
                int id = getCookieId("editPrediction", req, dao);
                Cookie cookie = (Cookie) dao.getById(id);
                PrintWriter out = resp.getWriter();
                out.println(cookie.getPrediction());
            }
            if(req.getParameter("sendEditedPrediction") != null){
                int id = getCookieId("sendEditedPrediction", req, dao);
                String[] parametersPred = req.getParameterValues("editedTextOfPrediction");
                String editedPrediction = parametersPred[0];
                Cookie cookie = new Cookie();
                cookie.setId(id);
                cookie.setPrediction(editedPrediction);
                H2CookieDao h2CookieDao = (H2CookieDao) dao;
                h2CookieDao.update(cookie);
                updatePredictionTable(h2CookieDao, req);
            }

        } catch (SQLException e) {
            log.error(e);
        }
    }

    private void updatePredictionTable(GenericDao dao, HttpServletRequest req){
        LinkedList<Cookie> cookies = (LinkedList<Cookie>) dao.getAll();
        String[] predictions = new String[cookies.size()];
        int i = 0;
        for (Cookie c : cookies) {
            predictions[i++] = c.getPrediction();
        }
        HttpSession session = req.getSession();
        PredictionsBean predictionsBean = new PredictionsBean();
        predictionsBean.setMessages(predictions);
        session.setAttribute("predictionsBean", predictionsBean);
    }

    private int getCookieId(String parameter, HttpServletRequest req, GenericDao dao) {
        String[] parameters = req.getParameterValues(parameter);
        int indexPrediction = Integer.parseInt(parameters[0]);
        LinkedList<Cookie> cookies = (LinkedList<Cookie>) dao.getAll();
        return cookies.get(indexPrediction - 1).getId();
    }
}