package controller.menu;

import controller.DiskController;
import view.View;

public class DiskMenuController {
	private final MainMenuController mainMenu;
	private final DiskController diskController;
	private final View view;
	private final CreateDiskMenu createDiskMenu;
	private final DiskInfoMenu diskInfo;

	public DiskMenuController(final MainMenuController mainMenu) {
		this.mainMenu = mainMenu;
		this.diskController = this.mainMenu.getDiskController();
		this.view = this.mainMenu.getView();
		this.createDiskMenu = new CreateDiskMenu(this);
		this.diskInfo = new DiskInfoMenu(this);
	}

	void diskMenu() {
		view.print("============================");
		view.print("\tDisk menu");
		view.print("============================");
		view.print("[1] Create disk | [2] Disk info | [3] Back to Main menu");
		switch (mainMenu.getChoice(3)) {
			case 1:
				createDiskMenu.createDiskMenu();
				diskMenu();
				break;
			case 2:
				diskInfo.diskInfo();
				diskMenu();
				break;
			case 3:
				mainMenu.mainMenu();
		}
	}

	MainMenuController getMainMenu() {
		return mainMenu;
	}

	View getView() {
		return view;
	}

	DiskController getDiskController() {
		return diskController;
	}
}
