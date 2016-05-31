package controller;


import dao.CookieDao;
import dao.Factory;
import dao.substance.Cookie;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/ControllerCookie"})
public class ControllerCookie extends HttpServlet {
    private Factory factory;
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(ControllerLogin.class);

    @Override
    public void init() throws ServletException {
        factory = (Factory) getServletContext().getAttribute("DBManager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cookie> cookies;

        cookies = getCookies(request);

        int cookiesLength = cookies.size();
        int id = getRandom(cookiesLength);
        for (Cookie cookie : cookies) {
            if (cookie.getId() == id) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(cookie);
                response.getWriter().write(jsonString);
            }
        }

        String move = request.getParameter("move");


        if ("next".equals(move)) {
            selectPages(request, response, 8,8);

        } else if("return".equals(move)){
            selectPages(request, response, 8,-8);
        }
    }

    private List<Cookie> getCookies(HttpServletRequest request) {
        List<Cookie> cookies = null;

        try (Connection connection = factory.getConnection()) {

            CookieDao cookieManager = factory.getCookieDao(connection);
            cookies = cookieManager.selectAll("cookies");

        } catch (InterruptedException | SQLException e) {
            log.error(e.getMessage(), e);
        }
        return cookies;
    }

    private void selectPages(HttpServletRequest req, HttpServletResponse resp, int limit,int offset) throws ServletException, IOException {
        List<Cookie> cookies;

        try (Connection connection = factory.getConnection()) {
            CookieDao cookieManager = factory.getCookieDao(connection);


            cookies = cookieManager.pagesSelect(limit,offset);
            req.setAttribute("predictions", cookies);
            req.getRequestDispatcher("site/pages/cookie_table.jsp").forward(req, resp);

        } catch (InterruptedException | SQLException e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        selectPages(req, resp, 8,0);

        String method = req.getParameter("method");
        if ("delete".equals(method)) {
            delete(req, resp);
        } else if ("update".equals(method)) {
            update(req, resp);
        }
    }

    private int getRandom(int cookiesLength) {

        return (int) (Math.random() * cookiesLength + 1);
    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = factory.getConnection()) {

            CookieDao cookieManager = factory.getCookieDao(connection);
            String prediction = req.getParameter("var3key");

            int row = cookieManager.delete(prediction);
            if (row > 0) {
                log.info("delete" + row);
            }
        } catch (InterruptedException | SQLException e) {
            log.error(e.getMessage(), e);
        }
    }


    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = factory.getConnection()) {

            CookieDao cookieManager = factory.getCookieDao(connection);
            String prediction = req.getParameter("prediction");
            String updatePediction = req.getParameter("predictionUpdate");

            int row = cookieManager.update(prediction, updatePediction);

            if (row > 0) {
                log.info("update" + row);

            }

        } catch (InterruptedException | SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}