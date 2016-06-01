package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Yuriy Krishtop on 31.05.2016.
 */

@WebServlet("/pageController")
public class PageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            int pageNum = Integer.parseInt(session.getAttribute("pageNumber").toString());
            pageNum++;
            session.setAttribute("pageNumber", pageNum);
        }
        if (req.getParameter("prevPage") != null) {
            int pageNum = Integer.parseInt(session.getAttribute("pageNumber").toString());
            pageNum--;
            session.setAttribute("pageNumber", pageNum);
        }
    }

}
