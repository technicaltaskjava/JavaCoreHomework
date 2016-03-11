package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ModelException;
import t01.model.Environment;

public class Cd implements Command {
    private static final String cmdName = "cd ";

    private final MainController controller;

    public Cd(final MainController controller) {
        this.controller = controller;
    }


    @Override
    public boolean canExecute(String input) {
        return input != null && input.startsWith(cmdName);
    }

    @Override
    public void execute(String input) {
        String path = input.substring(cmdName.length());
        try {
            Environment.setCurrentDir(path);
            controller.print("");
        } catch (ModelException e) {
            controller.print("\t" + e.getMessage());
        }
    }
}
