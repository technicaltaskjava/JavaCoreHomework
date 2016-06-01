package servlets;

import entities.PredictionsBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yuriy Krishtop on 23.05.2016.
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        if (session.getAttribute("login") != null) {
            out.println(session.getAttribute("login"));
        } else {
            out.println("Not authorized users in current session have been find");
        }
        if(req.getParameter("signOut") != null) {
            session.invalidate();
            PredictionsBean predictionsBean = new PredictionsBean();
            session.setAttribute("predictionsBean", predictionsBean);
        };
    }

}
