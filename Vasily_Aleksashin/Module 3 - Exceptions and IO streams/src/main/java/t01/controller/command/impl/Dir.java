package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ModelException;
import t01.model.directory.DirectoryInfo;
import t01.model.Environment;
import t01.model.directory.impl.DirectoryInfoImpl;

import java.nio.file.Path;

public class Dir implements Command {
    private static final String cmdName = "dir";

    private final DirectoryInfo dirInfo = new DirectoryInfoImpl();
    private final MainController controller;

    public Dir(final MainController mainController) {
        this.controller = mainController;
    }

    @Override
    public boolean canExecute(final String input) {
        return input != null && input.equals(cmdName);
    }
    @Override
    public void execute(final String input) {
        String path = Environment.getCurrentDir();
        try {
            Path[] stream = dirInfo.getDirStream(path);
            String result = this.dirInfo.getDirInfo(stream);
            controller.print(result);
        } catch (ModelException e) {
            controller.print("\t" + e.getMessage());
        }
    }
}
