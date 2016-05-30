package javase.web.servlets;

import javase.connectionpool.ConnectionPoolException;
import javase.dao.datasource.CookiesDAO;
import javase.dao.factory.DAOFactory;
import javase.constants.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javase.dao.transfer.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet to show and process Table page
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public class TableServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(TableServlet.class);

    @Override
    protected void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String logged = (String) session.getAttribute(Constants.LOGGED);

        if (logged == null || !Constants.SUCCESSFUL.equals(logged)) {
            response.sendRedirect("login");
            return;
        }
        String userName = (String) session.getAttribute("username");

        int currentPage = 1;
        int recordsPerPage = Constants.RECORDS_PER_PAGE;
        if(request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        CookiesDAO cookies = getCookiesDAO();
        if ("delete".equals(request.getParameter(Constants.COMMAND))) {
            deleteCookie(cookies, Integer.parseInt(request.getParameter("id")));
        }
        if ("add".equals(request.getParameter(Constants.COMMAND))) {
            addCookie(cookies, request.getParameter("name"), request.getParameter("message"));
            currentPage = 0;
        }
        if ("edit".equals(request.getParameter(Constants.COMMAND))) {
            editCookie(cookies, Integer.parseInt(request.getParameter("id")), request.getParameter("name"),
                    request.getParameter("message"));
        }
        int recordCount = getRecordCount(cookies);
        int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
        if (currentPage == 0 || currentPage > pageCount) {
            currentPage = pageCount;
        }
        List<Cookie> list = getListByLimit(cookies, (currentPage - 1 ) * recordsPerPage,
                recordsPerPage);
        closeCookiesDAO(cookies);

        request.setAttribute("list", list);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher("WEB-INF/displayTable.jsp").forward(request, response);
    }

    private CookiesDAO getCookiesDAO() {
        DAOFactory daoFactory;
        Connection connection;
        CookiesDAO cookies = null;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = daoFactory.getConnection();
            cookies = daoFactory.getCookiesDAO(connection);
        } catch (ConnectionPoolException e) {
            logger.error("Exception when getting CookiesDAO", e);
        }
        return cookies;
    }

    private void closeCookiesDAO(CookiesDAO cookies) {
        DAOFactory daoFactory;
        Connection connection;
        try {
            ServletContext context = getServletContext();
            daoFactory = (DAOFactory)context.getAttribute(Constants.DAOFACTORY);
            connection = cookies.getConnection();
            if (daoFactory != null && connection != null) {
                daoFactory.freeConnection(connection);
            }
        } catch (ConnectionPoolException e) {
            logger.error("Exception when closing cookiesDAO", e);
        }
    }

    private List<Cookie> getListByLimit(CookiesDAO cookies, int offset, int count) {
        List<Cookie> list = null;
        try {
            list = cookies.selectByLimit(offset, count);
        } catch (SQLException e) {
            logger.error("Exception when getting list", e);
        }
        return list;
    }

    private void deleteCookie(CookiesDAO cookies, int cookieId) {
        try {
            cookies.delete(cookieId);
        } catch (SQLException e) {
            logger.error("Exception when removing cookie record", e);
        }
    }

    private void addCookie(CookiesDAO cookies, String name, String message) {
        try {
            Cookie cookie = new Cookie();
            cookie.setCookie(name);
            cookie.setMessage(message);
            cookies.insert(cookie);
        } catch (SQLException e) {
            logger.error("Exception when adding cookie record", e);
        }
    }

    private void editCookie(CookiesDAO cookies, int id, String name, String message) {
        try {
            Cookie cookie = new Cookie();
            cookie.setId(id);
            cookie.setCookie(name);
            cookie.setMessage(message);
            cookies.update(cookie);
        } catch (SQLException e) {
            logger.error("Exception when updating cookie record", e);
        }
    }

    private int getRecordCount(CookiesDAO cookies) {
        int recordCount = 0;
        try {
            recordCount = cookies.getRecordCount();
        } catch (SQLException e) {
            logger.error("Exception when getting record count", e);
        }
        return recordCount;
    }
}