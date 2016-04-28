package controller.menu;

import controller.MainController;
import model.UserForm;
import model.XmlParser;
import model.datastore.UserData;
import model.exception.XMLParseException;
import model.parser.FormField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.InvalidKeyException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MainMenuController {
	static final String SEPARATOR = "=========================================";
	private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);

	public void show(final MainController controller) {
		final String menu = getMenu();
		boolean exitMenu = false;
		while (!exitMenu) {
			controller.print(menu);
			final String input = controller.read();
			switch (input) {
				case "0":
					SettingMenuController settingMenu = new SettingMenuController();
					settingMenu.show(controller);
					break;
				case "1":
					loadUserForm(controller);
					break;
				case "2":
					showDataStore(controller);
					break;
				case "3":
					exitMenu = true;
					controller.print("Thank you for using my App");
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 3", input));
			}
		}
	}

	private void loadUserForm(final MainController controller) {
		try {
			final String xmlDirPath = controller.getValue("xml.source");
			final File xmlDir = new File(xmlDirPath);
			final String[] xmlFiles = xmlDir.list();
			final XmlParser parser = controller.getParser();
			final UserData userData = controller.getUserData();
			for (String file : xmlFiles) {
				final Map<FormField, String> user = parser.parse(xmlDirPath + file);
				UserForm userForm = createUserForm(user);
				userData.add(userForm);
			}
		} catch (InvalidKeyException | XMLParseException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private UserForm createUserForm(final Map<FormField, String> user) {
		String firstName = null;
		String lastName = null;
		String userName = null;
		String email = null;
		String address = null;
		String language = null;

		final Set<FormField> userKey = user.keySet();
		for (FormField element : userKey) {
			switch (element) {
				case FIRSTNAME:
					firstName = user.get(element);
					break;
				case LASTNAME:
					lastName = user.get(element);
					break;
				case USERNAME:
					userName = user.get(element);
					break;
				case EMAIL:
					email = user.get(element);
					break;
				case ADDRESS:
					address = user.get(element);
					break;
				case LANGUAGE:
					language = user.get(element);
					break;
				default:
			}
		}
		final UserForm.Builder builder = new UserForm.Builder(firstName, lastName, userName);
		builder.email(email).address(address).language(language);
		return builder.build();
	}

	private void showDataStore(final MainController controller) {
		final UserData userData = controller.getUserData();
		final Collection<UserForm> userForms = userData.getAll();
		if (!userForms.isEmpty()) {
			for (UserForm element : userForms) {
				controller.print(element.toString());
			}
		} else {
			controller.print("UserData is EMPTY");
		}
	}

	private String getMenu() {
		return SEPARATOR + "\n" +
				"\tMAIN MENU\n" +
				SEPARATOR + "\n" +
				"[0] Application configuring\n" +
				"[1] Load user profile\n" +
				"[2] Show DataStore\n" +
				"[3] Exit\n" +
				SEPARATOR;
	}
}
