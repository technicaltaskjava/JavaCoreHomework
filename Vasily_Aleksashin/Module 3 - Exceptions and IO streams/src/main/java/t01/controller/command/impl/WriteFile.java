package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ControllerException;
import t01.exception.ModelException;
import t01.model.Environment;
import t01.model.file.FileOperations;
import t01.model.file.impl.FileOperationsImpl;

import java.io.File;

public class WriteFile implements Command {
	private static final String CMD_NAME = " > ";
	private static final String CMD_KEY = " -a";

	private final MainController controller;
	private final FileOperations operations;

	public WriteFile(final MainController controller) {
		this.controller = controller;
		operations = new FileOperationsImpl();
	}

	@Override
	public boolean canExecute(final String input) {
		return input != null && input.length() > CMD_NAME.length() && input.contains(CMD_NAME) && !input.endsWith(" ");
	}

	@Override
	public void execute(final String input) {
		String message = input.substring(0, input.indexOf(CMD_NAME));
		String tempFileName;
		boolean appendFlag = false;
		try {
			if (input.endsWith(CMD_KEY)) {
				appendFlag = true;
				tempFileName = input.substring(input.indexOf(CMD_NAME) + CMD_NAME.length(), input.indexOf(CMD_KEY));
			} else {
				tempFileName = input.substring(input.indexOf(CMD_NAME) + CMD_NAME.length());
			}
			if (tempFileName.length() == 0) {
				throw new ControllerException("Enter file name");
			}
			File file = new File(tempFileName);
			String fileName;
			if (!file.isAbsolute()) {
				fileName = Environment.getCurrentDir() + File.separatorChar + tempFileName;
			} else {
				fileName = tempFileName;
			}
			operations.write(fileName, message, appendFlag);
			controller.print("");
		} catch (ControllerException | ModelException e) {
			controller.print("\t" + e.getMessage());
		}
	}
}
