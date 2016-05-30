package servlets;

import db.access.dao.DataAccessObject;
import db.access.factory.DAOFactory;
import db.access.factory.sql.DatabaseAccessorFactory;
import db.connection.MyConnectionPool;
import db.storage.DataObject;
import db.storage.DataTypes;
import db.storage.data.Cookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PredictionGetter extends DatabaseHttpServlet {

    private static Logger logger = Logger.getLogger("Prediction servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "Oops.";
        try {
            DAOFactory factory = new DatabaseAccessorFactory((MyConnectionPool)getServletContext().getAttribute("ConnectionPool"));
            DataAccessObject cookieAccessor = factory.getDAO(DataTypes.COOKIE);
            List<DataObject> cookies = cookieAccessor.getData();
            Cookie cookie = (Cookie)cookies.get(ThreadLocalRandom.current().nextInt(0, cookies.size()-1));
            factory.close(cookieAccessor);
            data = cookie.getMessage();
        } catch (Exception e) {
            logger.log(Level.WARNING, "", e);
        }
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(data);
    }
}
