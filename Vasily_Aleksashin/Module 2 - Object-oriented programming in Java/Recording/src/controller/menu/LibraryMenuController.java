package controller.menu;

import model.entity.Record;
import model.entity.enums.Rating;
import model.service.MediaLibraryService;
import util.Util;
import view.View;

public class LibraryMenuController {
	private final MainMenuController mainMenu;
	private final MediaLibraryService libraryService;
	private final View view;

	public LibraryMenuController(final MainMenuController mainMenu) {
		this.mainMenu = mainMenu;
		this.libraryService = this.mainMenu.getLibraryService();
		this.view = this.mainMenu.getView();
	}

	void libraryMenu() {
		view.print("============================");
		view.print("\tLibrary menu");
		view.print("============================");
		view.print("[1] Show list of songs | [2] Search songs | [3] Back to Main menu");
		switch (mainMenu.getChoice(3)) {
			case 1:
				mainMenu.showRecords();
				libraryMenu();
				break;
			case 2:
				searchSong();
				libraryMenu();
				break;
			case 3:
				mainMenu.mainMenu();
		}
	}

	private void searchSong() {
		view.print("The search can be performed on any set of parameters");
		view.print("Each parameter must be separated by a symbol \"|\"");
		view.print("Please observe sequence of parameters");
		view.print("Parameters that are not to be used, leave blank");
		view.print("Example:Pretty Fly||00:03:04||5");
		Record[] records;
		while (true) {
			view.print("Available search parameter:");
			view.print("[title]|[author]|[duration]|[album]|[rating]");
			view.print("Enter your search query:");
			String input = view.read();
			String[] inputArray = mainMenu.parsStringToArray(input, 5);
			Long duration = null;
			if (!inputArray[2].equals("")) {
				duration = Util.StringTimeToLong(inputArray[2]) == 0 ? null : Util.StringTimeToLong(inputArray[2]);
				if (duration == null) {
					view.print(String.format("parameter 'duration' incorrect %s", inputArray[2]));
					continue;
				}
			}
			Integer rating = null;
			if (!inputArray[4].equals("")) {
				try {
					rating = Integer.parseInt(inputArray[4]);
				} catch (NumberFormatException e) {
					view.print(String.format("parameter 'rating' incorrect %s", inputArray[4]));
					continue;
				}
			}
			records = libraryService.getRecordsByParam(inputArray[0].equals("") ? null : inputArray[0],
					inputArray[1].equals("") ? null : inputArray[1],
					duration,
					inputArray[3].equals("") ? null : inputArray[3],
					Rating.getRating(rating));
			break;
		}
		view.print("Search results:");
		if (records.length != 0) {
			for (int index = 0; index < records.length; index++) {
				view.print("\t#" + (index + 1) + " " + records[index].toString());
			}
		} else {
			view.print("No search results");
		}
	}
}
