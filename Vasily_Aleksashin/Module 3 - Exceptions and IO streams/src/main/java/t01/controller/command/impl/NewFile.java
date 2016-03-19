package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ModelException;
import t01.model.Environment;
import t01.model.file.FileOperations;
import t01.model.file.impl.FileOperationsImpl;

import java.io.File;

public class NewFile implements Command {
	private static final String CMD_NAME = "newFile ";

	private final MainController controller;
	private final FileOperations operations;

	public NewFile(final MainController controller) {
		this.controller = controller;
		operations = new FileOperationsImpl();
	}

	@Override
	public boolean canExecute(final String input) {
		return input != null && input.startsWith(CMD_NAME);
	}

	@Override
	public void execute(final String input) {
		String tempFileName = input.substring(CMD_NAME.length());
		File file = new File(tempFileName);
		String fileName;
		if (!file.isAbsolute()) {
			fileName = Environment.getCurrentDir() + File.separatorChar + tempFileName;
		} else {
			fileName = tempFileName;
		}
		try {
			operations.create(fileName);
			controller.print("");
		} catch (ModelException e) {
			controller.print("\t" + e.getMessage());
		}
	}
}
