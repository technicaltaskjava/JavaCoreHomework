package controller.menu;

import controller.DiskController;
import controller.MainController;
import model.entity.Song;
import model.service.MediaLibraryService;
import view.View;

public class MainMenuController {
	private final MediaLibraryService libraryService;
	private final DiskController diskController;
	private final View view;
	private final MainController mainController;
	private final LibraryMenuController libraryMenu;
	private final DiskMenuController diskMenu;

	public MainMenuController(final MainController mainController) {
		this.mainController = mainController;
		this.libraryService = this.mainController.getLibraryService();
		this.diskController = this.mainController.getDiskController();
		this.view = this.mainController.getView();
		this.libraryMenu = new LibraryMenuController(this);
		this.diskMenu = new DiskMenuController(this);
	}

	public void mainMenu() {
		view.print("============================");
		view.print("\tMain menu");
		view.print("============================");
		view.print("[1] Media Library | [2] Disk menu | [3] Exit");
		switch (getChoice(3)) {
			case 1:
				libraryMenu.libraryMenu();
				break;
			case 2:
				diskMenu.diskMenu();
				break;
			case 3:
				mainController.exitApp();
		}
	}

	MediaLibraryService getLibraryService() {
		return libraryService;
	}

	DiskController getDiskController() {
		return diskController;
	}

	View getView() {
		return view;
	}

	String[] parsStringToArray(final String input, final int count) {
		String[] array = new String[count];
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (char c : input.toCharArray()) {
			if (c != '|') {
				builder.append(c);
			} else {
				array[index++] = builder.toString();
				builder = new StringBuilder();
			}

		}
		array[count - 1] = builder.toString();
		return array;
	}

	Song[] showRecords() {
		view.print("List of songs:");
		Song[] songs = libraryService.getSongs();
		for (int index = 0; index < songs.length; index++) {
			view.print("\t#" + (index + 1) + " " + songs[index].toString());
		}
		return songs;
	}

	int getChoice(final int countChoice) {
		while (true) {
			view.print("Enter menu item number:");
			String input = view.read().toLowerCase();
			try {
				int choice = Integer.parseInt(input);
				for (int index = 1; index <= countChoice; index++) {
					if (choice == index) {
						return choice;
					}
				}
				view.print("Incorrect menu, try again");
			} catch (NumberFormatException e) {
				view.print(String.format("Expected number, but received %s", input));
			}
		}
	}

	boolean getAnswer() {
		return mainController.getAnswer();
	}
}
