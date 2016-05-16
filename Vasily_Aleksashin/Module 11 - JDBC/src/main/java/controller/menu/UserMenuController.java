package controller.menu;

import controller.MainController;
import exception.CryptException;
import exception.DaoException;
import model.entity.User;
import model.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

class UserMenuController {
	private static final Logger logger = LoggerFactory.getLogger(UserMenuController.class);

	private UserService service;

	UserMenuController(MainController controller) throws DaoException {
		service = new UserService(controller.getFactory().getUserDao());
	}

	void show(MainController controller) {
		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					showAllUser(controller);
					break;
				case "1":
					findUserByName(controller);
					break;
				case "2":
					addNewUser(controller);
					break;
				case "3":
					updateUser(controller);
					break;
				case "4":
					deleteUser(controller);
					break;
				case "5":
					isExit = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 5", input));
			}
		}
	}

	private void deleteUser(MainController controller) {
		final User user = findUserByName(controller);
		if (user != null) {
			int result = service.delete(user);
			if (result != -1) {
				controller.print(String.format("User '%s' deleted", user.getUserName()));
			} else {
				controller.print(String.format("Cannot delete user '%s'", user.getUserName()));
			}
		}
	}

	private void updateUser(MainController controller) {
		final User user = findUserByName(controller);
		if (user == null) {
			controller.print("Cannot find user");
			return;
		}
		controller.print("Enter new user name or empty for not change:");
		final String userName = controller.read();
		if (!"".equals(userName)) {
			user.setUserName(userName);
		}
		String email = getEmail(controller);
		if (!"".equals(email)) {
			user.setEmail(email);
		}
		controller.print("Enter new password or empty for not change:");
		final String pass = controller.read();
		if (!"".equals(pass)) {
			user.setPassword(pass);
		}
		controller.print("Enter new first name or empty for not change:");
		final String firstName = controller.read();
		if (!"".equals(firstName)) {
			user.setFirstName(firstName);
		}
		controller.print("Enter new last name or empty for not change:");
		final String lastName = controller.read();
		if (!"".equals(lastName)) {
			user.setLastName(lastName);
		}
		int age = getAge(controller);
		if (age != 0) {
			user.setAge(age);
		}
		final int result = service.update(user);
		if (result != -1) {
			controller.print("User updated:");
			controller.print(user.toString());
		} else {
			controller.print("Cannot update user");
		}
	}

	private int getAge(MainController controller) {
		int age = 0;
		boolean isDone = false;
		while (!isDone) {
			controller.print("Enter new age or empty for not change:");
			final String input = controller.read();
			if ("".equals(input)) {
				break;
			}
			try {
				age = Integer.parseInt(input);
				ageValidate(age);
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("User age must be in interval 1-99, but found '%s'", input), e);
			}
		}
		return age;
	}

	private String getEmail(MainController controller) {
		String email = null;
		boolean isDone = false;
		while (!isDone) {
			controller.print("Enter new email or empty for not change:");
			email = controller.read();
			if ("".equals(email)) {
				break;
			}
			if (!email.matches("[\\w]*@[\\w\\W]*\\.[\\w]*")) {
				controller.print("Email incorrect");
			} else {
				isDone = true;
			}
		}
		return email;
	}

	private void ageValidate(int age) {
		if (age < 1 || age > 99) {
			throw new NumberFormatException();
		}
	}

	private void addNewUser(MainController controller) {
		controller.print("Enter user name:");
		final String userName = controller.read();
		boolean isDone = false;
		String email = null;
		while (!isDone) {
			controller.print("Enter email:");
			email = controller.read();
			if (!email.matches("[\\w]*@[\\w\\W]*\\.[\\w]*")) {
				controller.print("Email incorrect");
			} else {
				isDone = true;
			}
		}
		controller.print("Enter password:");
		final String pass = controller.read();
		controller.print("Enter first name:");
		final String firstName = controller.read();
		controller.print("Enter last name:");
		final String lastName = controller.read();
		int age = 0;
		isDone = false;
		while (!isDone) {
			controller.print("Enter age:");
			final String input = controller.read();
			try {
				age = Integer.parseInt(input);
				ageValidate(age);
				isDone = true;
			} catch (NumberFormatException e) {
				logger.error(String.format("User age must be in interval 1-99, but found '%s'", input), e);
			}
		}
		try {
			final User user = service.create(age, userName, email, pass, firstName, lastName);
			if (user != null) {
				controller.print("New user added:");
				controller.print(user.toString());
			} else {
				controller.print("Cannot create user");
			}
		} catch (CryptException e) {
			logger.error(e.getMessage(), e);
		}
	}

	User findUserByName(MainController controller) {
		controller.print("Enter user name:");
		final String input = controller.read();
		final User user = service.getByLogin(input);
		if (user != null) {
			controller.print(user.toString());
			return user;
		} else {
			controller.print(String.format("User by name '%s' not found", input));
			return null;
		}
	}

	List<User> showAllUser(MainController controller) {
		final List<User> users = service.getAll();
		controller.print("\tUsers list:");
		for (User user : users) {
			controller.print(user.toString());
		}
		if (users.isEmpty()) {
			controller.print("IS EMPTY");
			return Collections.emptyList();
		}
		return users;
	}

	private String getMenu() {
		StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
		builder.append("USER MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("[0] - Show all users").append("\n");
		builder.append("[1] - Find user by login").append("\n");
		builder.append("[2] - Add new user").append("\n");
		builder.append("[3] - Update user info").append("\n");
		builder.append("[4] - Delete user").append("\n");
		builder.append("[5] - Back to MAIN MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
