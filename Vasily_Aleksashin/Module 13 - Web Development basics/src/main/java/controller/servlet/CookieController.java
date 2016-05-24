package controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DaoFactory;
import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import model.entity.Cookie;
import model.service.impl.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = {"/getcookie"})
public class CookieController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(CookieController.class);
    private final Random random = new Random();
    private CookieService service;

    public CookieController() {
        try {
            DaoFactory factory = DaoFactoryImpl.getInstance();
            service = new CookieService(factory.getCookieDao());
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getRandomId(req.getParameter("id"));
        Cookie cookie = service.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        String resultOutput = mapper.writeValueAsString(cookie);
        resp.setContentType("application/json");
        resp.getWriter().write(resultOutput);
    }

    private int getRandomId(final String parameter) {
        Integer id = Integer.parseInt(parameter);
        int countCookie = service.getAll().size();
        int randomId;
        do {
            randomId = random.nextInt(countCookie);
        } while (randomId == 0 || randomId == id);
        if (id < 0) {
            return randomId;
        }
        return randomId;
    }
}
