package javase.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parent Servlet for child servlets implementation
 * Created by Yury Vislobodsky on 20.05.2016.
 */
public abstract class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processServlet(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Exception when do Get", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processServlet(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Exception when do Post", e);
        }
    }

    protected abstract void processServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}