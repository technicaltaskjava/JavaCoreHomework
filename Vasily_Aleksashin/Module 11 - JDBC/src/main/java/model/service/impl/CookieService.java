package model.service.impl;

import dao.impl.CookieDao;
import exception.DaoException;
import model.entity.Cookie;
import model.service.AbstractEntityService;

import java.util.List;

public class CookieService extends AbstractEntityService<Cookie, CookieDao> {
	public CookieService(final CookieDao entityDao) throws DaoException {
		super(entityDao);
	}

	public Cookie getByLuckyNumber(final int number) {
		final List<Cookie> cookies = getAll();
		for (Cookie cookie : cookies) {
			if (number == cookie.getLuckyNumber()) {
				return cookie;
			}
		}
		return null;
	}
}
