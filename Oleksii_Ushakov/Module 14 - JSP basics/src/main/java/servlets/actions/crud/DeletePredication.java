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

@WebServlet(urlPatterns = "/deletePredication")
public class DeletePredication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("indexArray[]");

        int[] indexes = convertArray(values);

        for (int index : indexes) {
            DAO.getInstance().getCookiesAccessor().deleteCookieByID(index);
        }
    }

    private int[] convertArray(String[] strings) {
        int[] array = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.valueOf(strings[i]);
        }

        return array;
    }
}
