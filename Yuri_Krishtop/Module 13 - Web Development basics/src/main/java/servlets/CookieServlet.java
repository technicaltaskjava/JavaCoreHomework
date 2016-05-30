package servlets;

import dao.DaoFactory;
import dao.GenericDao;
import dao.H2DaoFactory;
import entities.Cookie;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
            PrintWriter out = resp.getWriter();
            Random r = new Random();
            int index = r.nextInt(dao.getCountElements()) + 1;
            Cookie cookie = (Cookie) dao.getById(index);
            out.println(cookie.getPrediction());
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
