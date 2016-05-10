package controller;

import controller.menu.MainMenuController;
import dao.DaoFactory;
import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import view.View;
import view.impl.ConsoleViewImpl;

public class MainController {
	private final View view = new ConsoleViewImpl();
	private DaoFactory factory;

	void run() {
		try {
			factory = DaoFactoryImpl.getInstance(true);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		MainMenuController mainMenu = new MainMenuController();
		mainMenu.show(this);
	}


	public String read() {
		return view.read();
	}

	public void print(final String output) {
		view.print(output);
	}

	public DaoFactory getFactory() {
		return factory;
	}
}
