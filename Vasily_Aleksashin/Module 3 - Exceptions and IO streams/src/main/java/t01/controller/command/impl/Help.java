package t01.controller.command.impl;

import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ExitException;

public class Help implements Command {
    private static final String CMD_NAME = "help";

    private final MainController controller;

    public Help(MainController controller) {
        this.controller = controller;
    }

    @Override
    public boolean canExecute(String input) {
        return input != null && input.equals(CMD_NAME);
    }

    @Override
    public void execute(String input) throws ExitException {
        String msg = "Available Commands:\n" +
                "dir\n\tOutput the contents from the current directory to the screen\n" +
                "cd\n\tSyntax: cd [path]" +
		        "\n\t\t[path] relative path to the target directory from the current directory" +
                "\n\t\t.. indicates the parent directory.  Example: cd ..      - move up" +
                "\n\t\t.  indicates the current directory. Example: cd .\\dir  - goto dir in current path" +
                "\n\tChange the current directory\n" +
		        "newFile\n\tSyntax: newFile [[path]fileName]" +
		        "\n\t\t[path] relative path to the file from the current directory" +
		        "\n\tCreate new empty file in current directory\n" +
		        "read\n\tSyntax: read [[path]fileName]" +
		        "\n\t\t[path] relative path to the file from the current directory" +
		        "\n\tRead file in current directory\n" +
		        ">\n\tSyntax: [text] > [[path]fileName] [-a]" +
		        "\n\t\t[text] text that should be added to the file" +
		        "\n\t\t[path] relative path to the file from the current directory" +
		        "\n\t\t[-a]  command-line option, which performs the recording the text to the end of the file" +
		        "\n\tWrite file in current directory\n" +
                "exit\n\tExit from application";
        controller.print(msg);
    }
}
