package controller;

import dao.CookieDao;
import dao.Factory;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/insert"})
public class ControllerInsert extends HttpServlet{
        private static final long serialVersionUID = 1L;

        private static final Logger log = LoggerFactory.getLogger(ControllerLogin.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Factory factory = (Factory) getServletContext().getAttribute("DBManager");
        try (Connection connection = factory.getConnection()){

            CookieDao cookieManager = factory.getCookieDao(connection);
            String prediction = req.getParameter("insert_cookie");

            int row=cookieManager.insert(prediction);


            if (row > 0) {
                log.info("insert" + row);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/ControllerCookie");
                dispatcher.forward(req, resp);
            }

        } catch (InterruptedException | SQLException e) {
            log.error(e.getMessage(), e);
        }

    }


}
