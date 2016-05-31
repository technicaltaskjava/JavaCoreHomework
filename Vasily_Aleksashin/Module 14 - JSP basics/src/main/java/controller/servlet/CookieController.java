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
import java.util.List;
import java.util.Random;

@WebServlet("/service/cookie")
public class CookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CookieController.class);
	private static final String COOKIE_MSG = "cookieMsg";
	private static final String COOKIE_NUM = "cookieNum";
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
		String method = req.getParameter("method");
		if ("random".equals(method)) {
			getRandomCookie(req, resp);
		} else {
			showAdminPage(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if ("put".equals(method)) {
			doPut(req, resp);
		}
		if ("delete".equals(method)) {
			doDelete(req, resp);
		}
		if ("update".equals(method)) {
			updateCookie(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cookieMsg = req.getParameter(COOKIE_MSG);
		String cookieNum = req.getParameter(COOKIE_NUM);
		Cookie cookie = isExistCookie(cookieMsg);
		if (cookie != null) {
			resp.sendError(404);
		}
		Cookie addResult = service.create(cookieMsg, Integer.parseInt(cookieNum));
		if (addResult.getId() == null) {
			resp.sendError(404);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cookieMsg = req.getParameter(COOKIE_MSG);
		Cookie cookie = isExistCookie(cookieMsg);
		int deleteResult = -1;
		if (cookie != null) {
			deleteResult = service.delete(cookie);
		}
		if (cookie == null || deleteResult == -1) {
			resp.sendError(404);
		}
	}

	private void updateCookie(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		String cookieMsg = req.getParameter(COOKIE_MSG);
		String cookieNum = req.getParameter(COOKIE_NUM);
		String cookieMsgOld = req.getParameter("oldCookie");
		Cookie cookie = isExistCookie(cookieMsgOld);
		int updateResult = -1;
		if (cookie != null) {
			cookie.setCookieMessage(cookieMsg);
			cookie.setLuckyNumber(Integer.parseInt(cookieNum));
			updateResult = service.update(cookie);
		}
		if (cookie == null || updateResult == -1) {
			resp.sendError(404);
		}
	}

	private Cookie isExistCookie(final String cookieMsgOld) {
		return service.getByCookie(cookieMsgOld);
	}

	private void getRandomCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie cookie;
		do {
			int id = getRandomId(req.getParameter("id"));
			cookie = service.getById(id);
		} while (cookie == null);
		ObjectMapper mapper = new ObjectMapper();
		String resultOutput = mapper.writeValueAsString(cookie);
		resp.setContentType("application/json");
		resp.getWriter().write(resultOutput);
	}

	private int getRandomId(final String parameter) {
		Integer id = Integer.parseInt(parameter);
		int countCookie = service.getMaxId();
		int randomId;
		do {
			randomId = random.nextInt(countCookie + 1);
		} while (randomId == 0 || randomId == id);
		return randomId;
	}

	private void showAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int limit = Integer.parseInt(req.getServletContext().getInitParameter("limit"));
		String move = req.getParameter("move");
		int offset = getOffset(req.getParameter("offset"));
		int rowCount = service.getRowCount();
		if ("prev".equals(move)) {
			offset -= limit;
		}
		if ("next".equals(move)) {
			offset += limit;
		}
		if (offset < 0) {
			offset = 0;
		}
		if (offset >= rowCount) {
			offset = rowCount - limit;
		}
		List<Cookie> cookies = service.getCookieForPage(limit, offset);
		req.setAttribute("cookieList", cookies);
		req.setAttribute("count", rowCount);
		req.setAttribute("limit", limit);
		req.setAttribute("offset", offset);
		req.getRequestDispatcher("/admin").forward(req, resp);
	}

	private int getOffset(final String value) {
		return value != null ? Integer.parseInt(value) : 0;
	}
}
