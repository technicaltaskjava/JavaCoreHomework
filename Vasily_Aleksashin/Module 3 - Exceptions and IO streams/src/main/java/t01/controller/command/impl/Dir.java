package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ModelException;
import t01.model.DirectoryInfo;
import t01.model.impl.DirectoryInfoImpl;

import java.nio.file.Path;

public class Dir implements Command {
	private static final String cmdName = "dir ";

	private final DirectoryInfo dirInfo = new DirectoryInfoImpl();
	private final MainController controller;

	public Dir(final MainController mainController) {
		this.controller = mainController;
	}

	@Override
	public void execute(final String input) {
		if (input.startsWith(cmdName)) {
			String path = input.substring(cmdName.length());
			try {
				Path[] stream = dirInfo.getDirStream(path);
				String result = this.dirInfo.getDirInfo(stream);
				controller.print(result);
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
	}
}
