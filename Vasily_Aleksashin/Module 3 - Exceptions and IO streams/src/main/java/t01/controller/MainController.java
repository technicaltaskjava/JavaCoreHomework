package t01.controller;

import t01.controller.command.Command;
import t01.controller.command.impl.*;
import t01.exception.ExitException;
import t01.model.directory.ShellPrompt;
import t01.model.directory.impl.ShellPromptImpl;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;

public class MainController {
    private View view;
    private Command[] commands;

    public void run() throws ExitException {
        init();
        view.print("The application does not recognize Cyrillic\nEnter command or help");
        while (true) {
            String input = view.read();
            boolean flag = false;
            for (Command cmd : commands) {
                if (cmd.canExecute(input)) {
                    flag = true;
                    cmd.execute(input);
                }
            }
            if (!flag) {
                view.print("\tEnter wrong command");
            }
        }
    }

    public void print(final String output) {
        view.print(output);
    }

    private void init() {
        ShellPrompt prompt = ShellPromptImpl.getInstance();
        view = new ConsoleViewImpl(prompt);
        commands = new Command[]{new Exit(), new Dir(this), new Cd(this), new Help(this), new NewFile(this), new ReadFile(this), new WriteFile(this)};
    }
}
