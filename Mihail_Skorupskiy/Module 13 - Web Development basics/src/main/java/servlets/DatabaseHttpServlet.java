package servlets;

import db.connection.MyConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DatabaseHttpServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger("Abstract servlet");

    @Override
    public void init() throws ServletException {
        if (getServletContext().getAttribute("driver") == null){
            try {
                Class.forName("org.h2.Driver");
                getServletContext().setAttribute("driver", "h2");
            } catch (ClassNotFoundException e) {
                logger.log(Level.SEVERE, "Database driver not found", e);
            }
        }
        if (getServletContext().getAttribute("ConnectionPool") == null) {
            try {
                MyConnectionPool pool = new MyConnectionPool(5, "jdbc:h2:~/test", "sa", "");
                getServletContext().setAttribute("ConnectionPool", pool);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Database connection error", e);
            }
        }
        super.init();
    }
}
