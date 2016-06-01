package servlets;

import dao.DaoFactory;
import dao.H2DaoFactory;
import dao.H2MetadataDao;
import entities.Metadata;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yuriy Krishtop on 01.06.2016.
 */

@WebServlet("/metadata")
public class MetadataServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(MetadataServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory factory = new H2DaoFactory();
        try {
            Connection connection = (Connection) factory.getContext();
            H2MetadataDao dao = (H2MetadataDao) factory.getDao(connection, Metadata.class);
            resp.setContentType("text/html");
            if(req.getParameter("newMetadata") != null){
                HttpSession session = req.getSession();
                int userId = Integer.parseInt(session.getAttribute("userId").toString());
                int cookieId = Integer.parseInt(session.getAttribute("idLastCookie").toString());
                Metadata metadata = new Metadata();
                metadata.setUserId(userId);
                metadata.setCookieId(cookieId);
                dao.insert(metadata);
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
