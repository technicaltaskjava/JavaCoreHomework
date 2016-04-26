package controller;

import controller.menu.MainMenuController;
import model.Configuration;
import model.DataStore;
import model.XmlParser;
import model.datastore.DataFactory;
import model.datastore.UserData;
import model.parser.impl.DomParserImpl;
import view.View;
import view.impl.ConsoleViewImpl;

import java.security.InvalidKeyException;

public class MainController {
	private final DataFactory factory = new DataStore();
	private final View view = new ConsoleViewImpl();
	private Configuration configuration;
	private XmlParser parser = null;
	private UserData userData = null;


	void run() {
		configuration = Configuration.getInstance();
		parser = new XmlParser(new DomParserImpl());
		userData = factory.getSetData();
		MainMenuController mainMenu = new MainMenuController();
		mainMenu.show(this);
		view.close();
	}

	public String getValue(final String key) throws InvalidKeyException {
		return configuration.getProperty(key);
	}

	public void print(final String output) {
		view.print(output);
	}

	public String read() {
		return view.read();
	}

	public XmlParser getParser() {
		return parser;
	}

	public void setParser(final XmlParser parser) {
		this.parser = parser;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(final UserData userData) {
		this.userData = userData;
	}

	public DataFactory getFactory() {
		return factory;
	}
}
