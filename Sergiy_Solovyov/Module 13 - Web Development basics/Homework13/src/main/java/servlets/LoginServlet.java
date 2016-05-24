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
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


/**
 * @author Sergey Solovyov
 */

public class LoginServlet extends HttpServlet {

    private transient UserDAO userDAO;   // NOSONAR
    private static final String EMAIL = "email";
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/classes/html/login.html");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException |IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String email = req.getParameter(EMAIL);
        String password = req.getParameter("password");
        User user = null;
        try {
            if (userDAO != null)
            user = userDAO.getUserByEmail(email);
        } catch (SQLException e) {
            resp.addHeader("fail", "noUser");
            resp.addHeader(EMAIL, req.getParameter(EMAIL));
            LOGGER.info(e.getMessage(), e);
            return;
        }
          if (user != null){
              if (!password.equals(user.getPassword())){
                  resp.addHeader("fail", "failPassword");
              }
              else  {
                  HttpSession session = req.getSession();
                  session.setAttribute("nick", user.getUserName());
                  Cookie cookie = new Cookie("user", user.getUserName()); // NOSONAR  want secure cookie
                  resp.addCookie(cookie);
              }
          }
    }
}
