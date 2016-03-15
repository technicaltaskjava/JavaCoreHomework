package controller;

import controller.menu.MainMenuController;
import model.service.MediaLibraryService;
import model.service.impl.MediaLibraryServiceImpl;
import util.Util;
import view.ConsoleView;
import view.View;

import java.io.File;
import java.io.FileNotFoundException;

public class MainController {
	private static final String DEFAULT_LIBRARY_FILE = "library.txt";

	private File fileLibrary = null;
	private MediaLibraryService libraryService;
	private DiskController diskController;
	private final View view = new ConsoleView();

	public void run() {
		view.print("To close the application, enter the EXIT");
		view.print("The application must download the media library from file");

		while (true) {
			try {
				int loadSongCount = loadFile();
				if (loadSongCount == -1) {
					view.print("Something gone wrong, can not download the Media Library");
					view.print("Would you like to try again? (y/n)");
					if (getAnswer()) {
						continue;
					}
					exitApp();
				}
				view.print(String.format("Media library load successful with %s songs", loadSongCount));
				break;
			} catch (FileNotFoundException e) {
				view.print(String.format("Can not load media library from file '%s'", fileLibrary.getName()));
				view.print("Would you like to try again? (y/n)");
				if (getAnswer()) {
					continue;
				}
				exitApp();
			}
		}

		createDisk();
		view.print("Media disk created successful");
		view.print(String.format("\tAvailable disk duration %s",
				Util.longTimeToString(diskController.getDiskAvailableDuration())));

		MainMenuController mainMenuController = new MainMenuController(this);
		mainMenuController.mainMenu();

		exitApp();
	}

	public boolean getAnswer() {
		while (true) {
			switch (view.read().toLowerCase()) {
				case "n":
					return false;
				case "y":
					return true;
				case "exit":
					exitApp();
				default:
					view.print("Enter Y or N:");
			}
		}
	}

	public void exitApp() {
		view.closeResources();
		view.print("Thank you for using my APP");
		System.exit(0);
	}

	public MediaLibraryService getLibraryService() {
		return libraryService;
	}

	public DiskController getDiskController() {
		return diskController;
	}

	public View getView() {
		return view;
	}

	private int loadFile() throws FileNotFoundException {
		String fileName = DEFAULT_LIBRARY_FILE;
		view.print("Do you want to load default media library? (y/n):");
		if (!getAnswer()) {
			view.print("Enter media library file name:");
			fileName = view.read();
		}
		try {
			fileLibrary = new File(getClass().getClassLoader().getResource(fileName).getFile().replaceAll("%20", " "));
		} catch (NullPointerException e) {
			return -1;
		}
		libraryService = new MediaLibraryServiceImpl();
		return libraryService.update(fileLibrary);
	}

	private void createDisk() {
		view.print("Do you want create media disk? (y/n):");
		if (!getAnswer()) {
			exitApp();
			return;
		}
		view.print("Enter disk label:");
		String label = view.read();
		int minutes;
		while (true) {
			String read = null;
			try {
				view.print("Enter disk duration in minutes:");
				read = view.read();
				minutes = Integer.parseInt(read);
				break;
			} catch (NumberFormatException e) {
				view.print(String.format("Expected number, but received %s", read));
			}
		}
		diskController = new DiskController(label, minutes);
	}
}
