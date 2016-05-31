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

@WebServlet(urlPatterns = "/editPredication")
public class EditPredication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String predication = req.getParameter("value");
        int index = Integer.valueOf(req.getParameter("index"));

        DAO.getInstance().getCookiesAccessor().updateCookie(index, predication);
    }
}
