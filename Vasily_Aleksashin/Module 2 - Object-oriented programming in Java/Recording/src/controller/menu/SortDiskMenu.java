package controller.menu;

import controller.DiskController;
import model.entity.Record;
import view.View;

public class SortDiskMenu {
	private final MainMenuController mainMenu;
	private final DiskInfoMenu diskInfoMenu;
	private final DiskController diskController;
	private final View view;

	public SortDiskMenu(final DiskInfoMenu diskInfoMenu) {
		this.diskInfoMenu = diskInfoMenu;
		this.mainMenu = this.diskInfoMenu.getMainMenu();
		this.diskController = this.diskInfoMenu.getDiskController();
		this.view = this.diskInfoMenu.getView();
	}

	void sortTrack() {
		view.print("============================");
		view.print("\tSort track on disk menu");
		view.print("============================");
		view.print("[1] Sort by Title | [2] Sort by Author | [3] Sort by Duration | [4] Back to Disk Info menu");
		switch (mainMenu.getChoice(4)) {
			case 1:
				sortByTitle();
				sortTrack();
				break;
			case 2:
				sortByAuthor();
				sortTrack();
				break;
			case 3:
				sortByDuration();
				sortTrack();
				break;
			case 4:
				diskInfoMenu.diskInfo();
		}
	}

	private void sortByDuration() {
		view.print("Track on the disk before sort:");
		if (getDiskTrack()) {
			diskController.sortDiskTrackByDuration();
			view.print("Track on the disk after sort:");
			getDiskTrack();
		}
	}

	private void sortByAuthor() {
		view.print("Track on the disk before sort:");
		if (getDiskTrack()) {
			diskController.sortDiskTrackByAuthor();
			view.print("Track on the disk after sort:");
			getDiskTrack();
		}
	}

	private void sortByTitle() {
		view.print("Track on the disk before sort:");
		if (getDiskTrack()) {
			diskController.sortDiskTrackByTitle();
			view.print("Track on the disk after sort:");
			getDiskTrack();
		}
	}

	private boolean getDiskTrack() {
		Record[] records = diskController.getDiskRecords();
		if (records.length != 0) {
			for (int index = 0; index < records.length; index++) {
				view.print("\t#" + (index + 1) + " " + records[index].toString());
			}
		} else {
			view.print("The disk is empty");
			return false;
		}
		return true;
	}
}
