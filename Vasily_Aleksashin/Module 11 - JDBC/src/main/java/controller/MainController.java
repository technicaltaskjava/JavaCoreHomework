package controller;

import controller.menu.MainMenuController;
import dao.DaoFactory;
import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import model.conf.Configuration;
import view.View;
import view.impl.ConsoleViewImpl;

import java.security.InvalidKeyException;

public class MainController {
	private final View view = new ConsoleViewImpl();
	private DaoFactory factory;
	private Configuration conf = Configuration.getInstance();

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

	public String getProperty(final String key) throws InvalidKeyException {
		return conf.getProperty(key);
	}
}
