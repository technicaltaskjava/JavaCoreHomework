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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationController extends DatabaseHttpServlet {

    private static Logger logger = Logger.getLogger("Registration servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User(
                    req.getParameter("username"),
                    req.getParameter("email"),
                    req.getParameter("password")
            );
            DAOFactory factory = new DatabaseAccessorFactory((MyConnectionPool)getServletContext().getAttribute("ConnectionPool"));
            DataAccessObject userAccessor = factory.getDAO(DataTypes.USER);
            userAccessor.insertData(user);
            factory.close(userAccessor);
            req.getRequestDispatcher("index.html").forward(req, resp);
        } catch (Exception e) {
            logger.log(Level.WARNING, "", e);
        }
    }
}
