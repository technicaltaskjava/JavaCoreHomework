package t05.controller.menu;

import t01.exception.ModelException;

public class SerializeMenu {
	private MainMenu mainMenu;

//	public SerializeMenu(final MainMenu mainMenu) {
//		this.mainMenu = mainMenu;
//	}

	public void show(final MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		boolean flag = true;
		while (flag) {
		mainMenu.print(mainMenu.separator);
		String menu = "\tSERIALIZE MENU\n[1] Deserialize from default file | [2] Deserialize from user file\n" +
				"[3] Serialize to default file | [4] Serialize to user file | [5] back to Main Menu";
		mainMenu.print(menu);
		mainMenu.print(mainMenu.separator);
			mainMenu.print("Enter menu number:");
			String input = mainMenu.read();
			switch (input) {
				case "1":
					deserializeDefault();
					break;
				case "2":
					deserializeCustom();
					break;
				case "3":
					serializeDefault();
					break;
				case "4":
					serializeCustom();
					break;
				case "5":
					flag = false;
					break;
				default:
					mainMenu.print("Incorrect menu number, expected from 1 to 7");
			}
		}
	}

	private void serializeCustom() {
		mainMenu.print("Enter file path:");
		String filePath = mainMenu.read();
		try {
			mainMenu.getSerializeService().serialize(mainMenu.getMoviesService(), filePath);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void serializeDefault() {
		try {
			mainMenu.getSerializeService().serialize(mainMenu.getMoviesService(), null);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void deserializeCustom() {
		mainMenu.print("Enter file path:");
		String filePath = mainMenu.read();
		try {
			mainMenu.getSerializeService().deserialize(filePath);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void deserializeDefault() {
		try {
			mainMenu.getSerializeService().deserialize(null);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}
}
