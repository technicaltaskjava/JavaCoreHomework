package controller.servlet;

import dao.DaoFactory;
import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import model.entity.User;
import model.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private UserService service;

	public LoginController() {
		try {
			DaoFactory factory = DaoFactoryImpl.getInstance();
			service = new UserService(factory.getUserDao());
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
		try {
			String userName = req.getParameter("username");
			String pass = req.getParameter("pass");
			User user = service.getByLogin(userName);
			if (user == null || !user.getPassword().equals(pass)) {
				resp.sendError(401);
			} else {
				req.getSession(true);
				Cookie cookieAuth = new Cookie("auth", "true");
				cookieAuth.setMaxAge(-1);
				cookieAuth.setPath("/");
				Cookie cookieUserName = new Cookie("name", userName);
				cookieUserName.setMaxAge(-1);
				cookieUserName.setPath("/");
				resp.addCookie(cookieAuth);
				resp.addCookie(cookieUserName);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
