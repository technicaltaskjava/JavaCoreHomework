package controller.menu;

import controller.DiskController;
import model.entity.Record;
import model.entity.Song;
import util.Util;
import view.View;

public class CreateDiskMenu {
	private final MainMenuController mainMenu;
	private final DiskMenuController diskMenu;
	private final DiskController diskController;
	private final View view;

	public CreateDiskMenu(final DiskMenuController diskMenu) {
		this.diskMenu = diskMenu;
		this.mainMenu = this.diskMenu.getMainMenu();
		this.diskController = this.diskMenu.getDiskController();
		this.view = this.diskMenu.getView();
	}

	void createDiskMenu() {
		view.print("============================");
		view.print("\tCreate disk menu");
		view.print("============================");
		view.print("[1] Add songs to queue | [2] Show queue of songs | [3] Burn disk | [4] Back to Disk menu");
		switch (mainMenu.getChoice(4)) {
			case 1:
				addSongToQueue();
				createDiskMenu();
				break;
			case 2:
				showQueue();
				createDiskMenu();
				break;
			case 3:
				burnDisk();
				createDiskMenu();
				break;
			case 4:
				diskMenu.diskMenu();
		}
	}

	private void addSongToQueue() {
		view.print("Enter the number of songs to add to the disk");
		view.print("Each number must be separated by a symbol \",\"");
		Song[] songs = mainMenu.showRecords();
		boolean flag = true;
		while (flag) {
			view.print("Enter numbers of songs:");
			String[] input = view.read().split(",");
			if (input.length != 0) {
				for (int index = 0; index < input.length; index++) {
					if (!input[index].equals("")) {
						int number = 0;
						try {
							number = Integer.parseInt(input[index]);
							if (diskController.getDiskAvailableDuration() >= songs[number - 1].getDuration()) {
								diskController.addSongToDisk(songs[number - 1]);
							} else {
								view.print("Reached the maximum duration of the disk");
								flag = false;
								break;
							}
						} catch (NumberFormatException e) {
							view.print("\t" + e.getMessage());
						} catch (IndexOutOfBoundsException e) {
							view.print(String.format("Song #%s not found", number - 1));
						}
					}
				}
			}
			if (flag) {
				view.print("Do you want to add more songs? (y/n)");
				if (!mainMenu.getAnswer()) {
					flag = false;
				}
			}
		}
	}

	private void showQueue() {
		view.print("Queue of songs:");
		Record[] records = diskController.getRecordsForBurning();
		if (records.length == 0) {
			view.print("\tQueue is EMPTY");
		}
		for (int index = 0; index < records.length; index++) {
			view.print("\t#" + (index + 1) + " " + records[index].toString());
		}
		showDiskDuration();
	}

	private void showDiskDuration() {
		view.print("Information about the duration of the disk");
		view.print(String.format("Maximum the disk duration: %s", Util.longTimeToString(diskController.getDiskMaxDuration())));
		view.print(String.format("Used the disk duration: %s", Util.longTimeToString(diskController.getDiskCurrentDuration())));
		view.print(String.format("Available the disk duration: %s", Util.longTimeToString(diskController.getDiskAvailableDuration())));
	}

	private void burnDisk() {
		if (diskController.isDiskFinalized()) {
			view.print("\tThe disk is not writable");
		}
		if (diskController.getRecordsForBurning().length == 0) {
			view.print("\tDo not select a songs for recording");
		}
		if (diskController.getDiskAvailableDuration() < 0) {
			view.print("\tSelected songs more than the allowable duration");
		}
		diskController.burnSongsToDisk();
		view.print("Disk successfully burned");
	}
}
