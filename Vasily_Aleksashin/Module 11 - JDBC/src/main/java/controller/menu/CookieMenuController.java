package controller.menu;

import controller.MainController;
import exception.DaoException;
import model.entity.Cookie;
import model.service.impl.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class CookieMenuController {
	private static final Logger logger = LoggerFactory.getLogger(UserMenuController.class);

	private CookieService service;

	CookieMenuController(MainController controller) throws DaoException {
		service = new CookieService(controller.getFactory().getCookieDao());
	}

	void show(MainController controller) {
		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					showAllCookies(controller);
					break;
				case "1":
					findCookieByLuckyNumber(controller);
					break;
				case "2":
					addNewCookie(controller);
					break;
				case "3":
					updateCookie(controller);
					break;
				case "4":
					deleteCookie(controller);
					break;
				case "5":
					isExit = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 5", input));
			}
		}
	}

	private void deleteCookie(MainController controller) {
		int id = getCookieById(controller);
		final Cookie cookie = service.getById(--id);
		if (cookie != null) {
			final int result = service.delete(cookie);
			if (result != -1) {
				controller.print(String.format("Cookie '%s' deleted", cookie.getId()));
			} else {
				controller.print(String.format("Cannot delete cookie '%s'", cookie.getId()));
			}
		}
	}

	private void updateCookie(MainController controller) {
		int id = getCookieById(controller);
		final Cookie cookie = service.getById(--id);
		if (cookie == null) {
			controller.print("Cannot find cookie");
			return;
		}
		controller.print("Enter new cookie or empty for not change:");
		final String cookieText = controller.read();
		if (!"".equals(cookieText)) {
			cookie.setCookie(cookieText);
		}
		int number = getNumber(controller);
		if (number != 0) {
			cookie.setLuckyNumber(number);
		}
		final int result = service.update(cookie);
		if (result != -1) {
			controller.print("Cookie updated:");
			controller.print(cookie.toString());
		} else {
			controller.print("Cannot update cookie");
		}
	}

	private int getNumber(MainController controller) {
		int number = 0;
		boolean isDone = false;
		while (!isDone) {
			controller.print("Enter new lucky number or empty for not change:");
			final String input = controller.read();
			if ("".equals(input)) {
				break;
			}
			try {
				number = Integer.parseInt(input);
				if (number < 1) {
					throw new NumberFormatException();
				}
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("Lucky number must be positive, but found '%s'", input), e);
			}
		}
		return number;
	}

	private int getCookieById(MainController controller) {
		boolean isDone = false;
		int id = 0;
		while (!isDone) {
			controller.print("Enter cookie ID:");
			final String input = controller.read();
			try {
				id = Integer.parseInt(input);
				if (id < 1) {
					throw new NumberFormatException();
				}
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("Cookie ID must be positive, but found '%s'", input), e);
			}
		}
		return id;
	}

	private void addNewCookie(MainController controller) {
		controller.print("Enter cookie text:");
		final String cookieText = controller.read();
		boolean isDone = false;
		int number = 0;
		while (!isDone) {
			controller.print("Enter lucky number:");
			final String input = controller.read();
			try {
				number = Integer.parseInt(input);
				if (number < 0) {
					throw new NumberFormatException();
				}
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("Lucky number must be positive, but found '%s'", input), e);
			}
		}
		final Cookie cookie = service.create(cookieText, number);
		if (cookie != null) {
			controller.print("New cookie added:");
			controller.print(cookie.toString());
		} else {
			controller.print("Cannot create cookie");
		}
	}

	private void findCookieByLuckyNumber(MainController controller) {
		boolean isDone = false;
		int number = 0;
		while (!isDone) {
			controller.print("Enter lucky number:");
			final String input = controller.read();
			try {
				number = Integer.parseInt(input);
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("Lucky number must be digit, but found '%s'", input), e);
			}
		}
		final Cookie cookie = service.getByLuckyNumber(number);
		if (cookie != null) {
			controller.print(cookie.toString());
		} else {
			controller.print(String.format("Cookie by lucky number '%s' not found", number));
		}
	}

	List<Cookie> showAllCookies(MainController controller) {
		final List<Cookie> cookies = service.getAll();
		controller.print("\tCookies list:");
		for (Cookie cookie : cookies) {
			controller.print(cookie.toString());
		}
		if (cookies.isEmpty()) {
			controller.print("IS EMPTY");
			return Collections.emptyList();
		}
		return cookies;
	}

	private String getMenu() {
		StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
		builder.append("COOKIE MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("[0] - Show all cookies").append("\n");
		builder.append("[1] - Find cookie by lucky number").append("\n");
		builder.append("[2] - Add new cookie").append("\n");
		builder.append("[3] - Update cookie info").append("\n");
		builder.append("[4] - Delete cookie").append("\n");
		builder.append("[5] - Back to MAIN MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
