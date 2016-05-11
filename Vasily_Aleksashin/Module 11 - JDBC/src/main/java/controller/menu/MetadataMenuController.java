package controller.menu;

import controller.MainController;
import exception.DaoException;
import model.entity.Cookie;
import model.entity.Metadata;
import model.entity.User;
import model.service.impl.MetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

class MetadataMenuController {
	private static final Logger logger = LoggerFactory.getLogger(UserMenuController.class);

	private MetadataService service;

	void show(MainController controller, UserMenuController userMenu, CookieMenuController cookieMenu) {
		try {
			service = new MetadataService(controller.getFactory().getMetadataDao());
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}

		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					showAllMetadata(controller);
					break;
				case "1":
					getCookieForUser(controller, userMenu, cookieMenu);
					break;
				case "2":
					changeCookieForUser(controller, userMenu, cookieMenu);
					break;
				case "3":
					deleteMetadataEntry(controller);
					break;
				case "4":
					eraseMetadata(controller);
					break;
				case "5":
					isExit = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 5", input));
			}
		}
	}

	private void eraseMetadata(MainController controller) {
		service.erase();
		controller.print("Metadata erased");
	}

	private void deleteMetadataEntry(MainController controller) {
		int id = getId(controller, "metadata", true);
		final Metadata metadata = service.getById(--id);
		if (metadata != null) {
			final int result = service.delete(metadata);
			if (result != -1) {
				controller.print("Metadata deleted");
			} else {
				controller.print("Cannot delete metadata");
			}
		} else {
			controller.print(String.format("Cannot find metadata by ID '%s'", ++id));
		}
	}

	private void changeCookieForUser(MainController controller, UserMenuController userMenu, CookieMenuController cookieMenu) {
		int id = getId(controller, "metadata", true);
		final Metadata metadata = service.getById(--id);
		if (metadata == null) {
			controller.print(String.format("Cannot find metadata by ID '%s'", ++id));
			return;
		}
		controller.print(metadata.toString());
		controller.print("Enter new user ID or empty for not change");
		int userId = getId(controller, "user", false);
		if (userId != 0) {
			final List<User> users = userMenu.showAllUser(controller);
			for (User user : users) {
				if (user.getId().equals(userId)) {
					metadata.setUser(user);
				}
			}
		}
		controller.print("Enter new cookie ID or empty for not change");
		int cookieId = getId(controller, "cookie", false);
		if (cookieId != 0) {
			final List<Cookie> cookies = cookieMenu.showAllCookies(controller);
			for (Cookie cookie : cookies) {
				if (cookie.getId().equals(cookieId)) {
					metadata.setCookie(cookie);
				}
			}
		}
		final int result = service.update(metadata);
		if (result != -1) {
			controller.print("Metadata updated");
		} else {
			controller.print("Cannot update metadata");
		}
	}

	private int getId(final MainController controller, final String field, boolean isEmpty) {
		boolean isDone = false;
		int id = 0;
		while (!isDone) {
			controller.print(String.format("Enter %s ID:", field));
			final String input = controller.read();
			if (!isEmpty && "".equals(input)) {
				return id;
			}
			try {
				id = Integer.parseInt(input);
				if (id < 1) {
					throw new NumberFormatException();
				}
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("ID must be positive, but found '%s'", input), e);
			}
		}
		return id;
	}

	private void getCookieForUser(MainController controller, UserMenuController userMenu, CookieMenuController cookieMenu) {
		final User user = userMenu.findUserByName(controller);
		final List<Cookie> cookies = cookieMenu.showAllCookies(controller);
		Random random = new Random();
		final int index = random.nextInt(cookies.size());
		Metadata metadata = new Metadata();
		metadata.setUser(user);
		metadata.setCookie(cookies.get(index));
		final Metadata result = service.add(metadata);
		if (result != null) {
			controller.print(String.format("For user '%s' selected cookie '%s'", user.getUserName(), cookies.get
					(index)));
		} else {
			controller.print("Cannot get cookie for user");
		}
	}

	private void showAllMetadata(MainController controller) {
		final List<Metadata> metadata = service.getAll();
		controller.print("\tMetadata list:");
		for (Metadata entry : metadata) {
			controller.print(entry.toString());
		}
		if (metadata.isEmpty()) {
			controller.print("IS EMPTY");
		}
	}

	private String getMenu() {
		StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
		builder.append("METADATA MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("[0] - Show all metadata").append("\n");
		builder.append("[1] - Get cookie for user").append("\n");
		builder.append("[2] - Change cookie for user").append("\n");
		builder.append("[3] - Delete metadata entry").append("\n");
		builder.append("[4] - Erase metadata").append("\n");
		builder.append("[5] - Back to MAIN MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
