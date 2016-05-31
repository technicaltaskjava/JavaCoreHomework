package servlets;

import daolayer.dao.UserDAO;
import daolayer.dao.factory.DAOFactory;
import daolayer.entity.User;
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



/**
 * @author Sergey Solovyov
 */

public class RegistrationServlet extends HttpServlet {

    private transient UserDAO userDAO;   // NOSONAR
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        DAOFactory  daoFactory = (DAOFactory)context.getAttribute(ContextListener.DAO_FACTORY);
        try {
            userDAO = daoFactory.getUserDAO();
        } catch (ConnectionPoolException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/classes/html/registration.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException |IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

            String nick = req.getParameter("nick");
            User user = new User();
            user.setEmail(req.getParameter("email"));
            user.setUserName(nick);
            user.setPassword(req.getParameter("password"));
        try {
            user.setAge(Integer.parseInt(req.getParameter("age")));
        } catch (NumberFormatException e) {
            LOGGER.info(e.getMessage(), e);
        }

        int result = 0;
        try {
            if (userDAO != null)
            result = userDAO.insertUser(user);
        } catch (SQLException e) {

            LOGGER.info(e.getMessage(), e);
        }

        if (result > 0)
                resp.addHeader("nick", nick);

    }
}
