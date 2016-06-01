package servlets;

import daolayer.dao.CookieDAO;
import daolayer.dao.factory.DAOFactory;
import daolayer.entity.Cookie;
import daolayer.exeptions.ConnectionPoolException;
import listener.ContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lammi on 27.05.2016.
 */
public class AdminServlet extends HttpServlet {

    private transient CookieDAO cookieDAO;   // NOSONAR

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServlet.class);

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        DAOFactory daoFactory = (DAOFactory)context.getAttribute(ContextListener.DAO_FACTORY);
        try {
            cookieDAO = daoFactory.getCookieDAO();
        } catch (ConnectionPoolException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookieText = req.getParameter("cookie");
        Cookie cookie = new Cookie();
        cookie.setCookieText(cookieText);
        try {
             cookieDAO.insertCookie(cookie);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        process(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in put");
        String editedCookie = req.getHeader("cook");

        String[] pathInfo = req.getPathInfo().split("/");
        int id = Integer.parseInt(pathInfo[2].trim());
        Cookie cookie = null;
        try {
             cookie = cookieDAO.getCookieById(id);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        if(cookie != null)
         cookie.setCookieText(editedCookie);
        try {
            cookieDAO.updateCookie(cookie);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");

        try {
            int id = Integer.parseInt(pathInfo[2].trim());
            System.out.println(id);
            cookieDAO.deleteCookie(id);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }
    private void process(HttpServletRequest req, HttpServletResponse resp){
        int page = 1;
        int recordsPerPage = 10;
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));



        try {
            List<Cookie> cookieList = cookieDAO.findAll((page-1)*recordsPerPage,
                    recordsPerPage);
            int noOfRecords = cookieDAO.getNoOfRecords();
            System.out.println(noOfRecords+"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFff");
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("cookieList", cookieList);
            req.setAttribute("cookie", new Cookie());

        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/classes/html/admin.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException |IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

}
