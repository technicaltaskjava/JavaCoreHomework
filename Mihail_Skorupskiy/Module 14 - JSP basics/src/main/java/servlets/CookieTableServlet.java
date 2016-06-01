package servlets;

import db.access.dao.DataAccessObject;
import db.access.factory.DAOFactory;
import db.access.factory.sql.DatabaseAccessorFactory;
import db.connection.MyConnectionPool;
import db.storage.DataTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CookieTableServlet extends DatabaseHttpServlet {

    private static Logger logger = Logger.getLogger("Cookie table servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession() != null){
            try {
                DAOFactory factory = new DatabaseAccessorFactory((MyConnectionPool) getServletContext().getAttribute("ConnectionPool"));
                DataAccessObject cookieAccessor = factory.getDAO(DataTypes.COOKIE);
                req.setAttribute("CookieList", cookieAccessor.getData());
                req.getRequestDispatcher("cookies.jsp").forward(req, resp);
            } catch (Exception e){
                logger.log(Level.WARNING, "", e);
            }
        }
    }
}
