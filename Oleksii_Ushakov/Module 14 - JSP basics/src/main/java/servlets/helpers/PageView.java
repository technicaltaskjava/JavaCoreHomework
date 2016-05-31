package servlets.helpers;

import dao.util.Predication;
import dao.util.PredicationList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author augustprime
 */

@WebServlet(urlPatterns = "/pageView")
public class PageView extends HttpServlet {
    private int pageNumber = 1;
    private static final int PREDICTIONS_ON_PAGE = 10;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pageNumber = Integer.valueOf(req.getParameter("pageNumber"));

        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageNumber > PredicationList.getSize() / PREDICTIONS_ON_PAGE) {
            pageNumber = PredicationList.getSize() / PREDICTIONS_ON_PAGE;
        }

        System.out.println(pageNumber);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Predication[] predicationArray = getPredictionsForPage();
        Cookie predictions = new Cookie("predictions", Arrays.toString(predicationArray));
        resp.addCookie(predictions);

        super.doGet(req, resp);
    }

    private static int getStartPosition(int pageNumber) {
        return ((pageNumber - 1) * PREDICTIONS_ON_PAGE) + 1;
    }

    private static int getEndPosition(int from) {
        int size = PredicationList.getSize();

        if ((from + PREDICTIONS_ON_PAGE) < size) {
            return from + PREDICTIONS_ON_PAGE;
        } else {
            return from + (size - from);
        }
    }

    private Predication[] getPredictionsForPage() {
        int start = getStartPosition(pageNumber);
        int end = getEndPosition(start);

        Predication[] predicationArray = new Predication[end - start];

        for (int i = start - 1; i < end - 1; i++) {
            predicationArray[i] = PredicationList.getPredication(i);
        }

        return predicationArray;
    }
}
