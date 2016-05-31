package servlets.actions.crud;

import dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/addNewPredication")
public class AddNewPredicationAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String predication = req.getParameter("newPredication");
        DAO.getInstance().getCookiesAccessor().addCookie(predication);
    }
}
