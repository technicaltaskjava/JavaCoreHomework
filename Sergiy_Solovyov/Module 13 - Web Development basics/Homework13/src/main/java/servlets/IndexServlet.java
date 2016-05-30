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
import java.util.Random;


/**
 * @author Sergey Solovyov
 */
public class IndexServlet extends HttpServlet {

    private transient CookieDAO cookieDAO;   // NOSONAR
    private static final long serialVersionUID = 1L;
    private static Random generator = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServlet.class);

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        DAOFactory  daoFactory = (DAOFactory)context.getAttribute(ContextListener.DAO_FACTORY);
        try {
            cookieDAO = daoFactory.getCookieDAO();
        } catch (ConnectionPoolException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/classes/html/index.html");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException |IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp)  {

        List<Cookie> cookies = null;
        try {
            if (cookieDAO != null)
            cookies = cookieDAO.findAll();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        if (cookies != null){
        resp.addHeader("cookie", cookies.get(getRandomInt(cookies.size())).getCookieText());
        }


    }

    public static int getRandomInt(int max){
        return generator.nextInt(max);
    }


}
