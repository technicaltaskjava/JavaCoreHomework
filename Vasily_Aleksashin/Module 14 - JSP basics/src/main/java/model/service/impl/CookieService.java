package model.service.impl;

import dao.impl.CookieDao;
import exception.DaoException;
import model.entity.Cookie;
import model.service.AbstractEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

import java.util.Collections;
import java.util.List;

public class CookieService extends AbstractEntityService<Cookie, CookieDao> {
	private static final Logger logger = LoggerFactory.getLogger(CookieService.class);

	public CookieService(final CookieDao entityDao) {
		super(entityDao);
	}

	public Cookie create(final String cookieText, final int luckyNumber) {
		Validator.isNull(cookieText);
		Cookie cookie = new Cookie();
		cookie.setCookieMessage(cookieText);
		cookie.setLuckyNumber(luckyNumber);
		return add(cookie);
	}

	public Cookie getByCookie(final String cookieText) {
		final List<Cookie> cookies = getAll();
		for (Cookie cookie : cookies) {
			if (cookie.getCookieMessage().equals(cookieText)) {
				return cookie;
			}
		}
		return null;
	}

	public int getRowCount() {
		int count = -1;
		try {
			count = entityDao.getRowCount();
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	public List<Cookie> getCookieForPage(final int limit, final int offset) {
		List<Cookie> cookies = null;
		try {
			cookies = entityDao.getCookieForPage(limit, offset);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		return cookies == null ? Collections.<Cookie>emptyList() : cookies;
	}

	public int getMaxId() {
		int count = -1;
		try {
			count = entityDao.getMaxId();
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}
}
