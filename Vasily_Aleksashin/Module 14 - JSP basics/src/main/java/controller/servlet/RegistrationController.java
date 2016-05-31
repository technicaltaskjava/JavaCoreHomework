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

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	private UserService service;

	public RegistrationController() {
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
			String userName = req.getParameter("userName");
			String email = req.getParameter("email");
			String pass = req.getParameter("password");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			int age = Integer.parseInt(req.getParameter("age"));
			User user = service.create(age, userName, email, pass, firstName, lastName);
			if (user.getId() != null) {
				req.getSession(true);
				addCookie(resp, user);
			} else {
				resp.sendError(401);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addCookie(final HttpServletResponse resp, final User user) {
		Cookie cookieAuth = new Cookie("auth", "true");
		cookieAuth.setMaxAge(-1);
		cookieAuth.setPath("/");
		Cookie cookieUserName = new Cookie("name", user.getUserName());
		cookieUserName.setMaxAge(-1);
		cookieUserName.setPath("/");
		resp.addCookie(cookieAuth);
		resp.addCookie(cookieUserName);
	}
}
