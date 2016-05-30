package servlets;

import db.access.dao.DataAccessObject;
import db.access.factory.DAOFactory;
import db.access.factory.sql.DatabaseAccessorFactory;
import db.connection.MyConnectionPool;
import db.storage.DataTypes;
import db.storage.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends DatabaseHttpServlet {

    private static Logger logger = Logger.getLogger("Login servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            DAOFactory factory = new DatabaseAccessorFactory((MyConnectionPool)getServletContext().getAttribute("ConnectionPool"));
            DataAccessObject userAccessor = factory.getDAO(DataTypes.USER);
            user = (User) userAccessor.findData(req.getParameter("username"));
            factory.close(userAccessor);
        } catch (Exception e) {
            logger.log(Level.WARNING, "", e);
        }
        if (user == null || !user.getPassword().equals(req.getParameter("password"))){
            resp.sendError(401);
        } else {
            if (getServletContext().getAttribute(user.getUsername()) == null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("User", user.getUsername());
                getServletContext().setAttribute(user.getUsername(), session);
            }
            req.getRequestDispatcher("cookies.html").forward(req, resp);
        }
    }
}
