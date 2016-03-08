package controller.menu;

import controller.DiskController;
import model.entity.Record;
import util.Util;
import view.View;

public class DiskInfoMenu {
	private final MainMenuController mainMenu;
	private final DiskMenuController diskMenu;
	private final DiskController diskController;
	private final View view;
	private final SortDiskMenu sortDiskMenu;

	public DiskInfoMenu(final DiskMenuController diskMenu) {
		this.diskMenu = diskMenu;
		this.mainMenu = diskMenu.getMainMenu();
		this.diskController = diskMenu.getDiskController();
		this.view = diskMenu.getView();
		this.sortDiskMenu = new SortDiskMenu(this);
	}

	public MainMenuController getMainMenu() {
		return mainMenu;
	}

	public DiskController getDiskController() {
		return diskController;
	}

	public View getView() {
		return view;
	}

	void diskInfo() {
		view.print("============================");
		view.print("\tDisk Info menu");
		view.print("============================");
		view.print("[1] Show disk duration | [2] Sort track on disk | [3] Search track on disk | [4] Back to Disk menu");
		switch (mainMenu.getChoice(4)) {
			case 1:
				showDiskDuration();
				diskInfo();
				break;
			case 2:
				sortDiskMenu.sortTrack();
				diskInfo();
				break;
			case 3:
				searchTrack();
				diskInfo();
				break;
			case 4:
				diskMenu.diskMenu();
		}
	}

	private void showDiskDuration() {
		view.print("Information about the duration of the disk");
		view.print(String.format("Maximum the disk duration: %s", Util.longTimeToString(diskController.getDiskMaxDuration())));
		view.print(String.format("Used the disk duration: %s", Util.longTimeToString(diskController.getDiskCurrentDuration())));
		view.print(String.format("Available the disk duration: %s", Util.longTimeToString(diskController.getDiskAvailableDuration())));
	}

	private void searchTrack() {
		view.print("The search can be performed on any set of parameters");
		view.print("Each parameter must be separated by a symbol \"|\"");
		view.print("Please observe sequence of parameters");
		view.print("Parameters that are not to be used, leave blank");
		view.print("Example:Pretty Fly||00:03:04");
		Record[] records;
		while (true) {
			view.print("Available search parameter:");
			view.print("[title]|[author]|[duration]");
			view.print("Enter your search query:");
			String input = view.read();
			String[] inputArray = mainMenu.parsStringToArray(input, 3);
			Long duration = null;
			if (!inputArray[2].equals("")) {
				duration = Util.StringTimeToLong(inputArray[2]) == 0 ? null : Util.StringTimeToLong(inputArray[2]);
				if (duration == null) {
					view.print(String.format("parameter 'duration' incorrect %s", inputArray[2]));
					continue;
				}
			}

			records = diskController.findRecords(inputArray[0].equals("") ? null : inputArray[0],
					inputArray[1].equals("") ? null : inputArray[1], duration);
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
