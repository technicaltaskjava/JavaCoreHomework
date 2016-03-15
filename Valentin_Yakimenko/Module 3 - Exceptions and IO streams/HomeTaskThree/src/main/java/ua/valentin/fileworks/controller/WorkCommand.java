package ua.valentin.fileworks.controller;

import ua.valentin.fileworks.exceptions.CreateFileException;
import ua.valentin.fileworks.exceptions.FileReadException;
import ua.valentin.fileworks.io.FileWork;
import ua.valentin.fileworks.exceptions.FileWriteException;
import ua.valentin.fileworks.io.IO;

import java.io.IOException;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public enum WorkCommand implements IWCommand {
    EXIT(1, "exit", "Exit from program", "have no parameters") {
        public String commandOutput(String option) {
            if (option != null) return EXIT.wrongParameter;
            else return EXIT.description;
        }
    },

    VIEW(3, "view", "view directory or file" , "use view <dir or file name>") {
        public String commandOutput(String option) {
            if (option == null) return VIEW.wrongParameter;
            else try {
                return fileWork.view(option);
            } catch (FileReadException e) {
                return e.getMessage();
            }
        }
    },

    WORK_FILE(2, "wf", "work with text file" , "use wf <filename>") {
        public String commandOutput(String option) {
            if (option == null) return WORK_FILE.wrongParameter;
            else try {
                return fileWork.usingFile(option, IO.askAndGetText());
            } catch (FileReadException | CreateFileException | FileWriteException | IOException e) {
                return e.getMessage();
            }
        }
    },

    REMOVE_FILE(2, "rf", "remove text file" , "use rf <filename>") {
        public String commandOutput(String option) {
            if (option == null) return REMOVE_FILE.wrongParameter;
            else return fileWork.removeFile(option);
        }
    },

    HELP(1, "help", "view all commands" , "have no parameters") {
        public String commandOutput(String option) {
            if (option != null) return HELP.wrongParameter;
            else return getAllCommands();
        }
    },

    WRONG_COMMAND(0, "wrong", "wrong command, type - \"help\", for help" , "have no parameters") {
        public String commandOutput(String option) {
            if (option != null) return EXIT.wrongParameter;
            else return WRONG_COMMAND.description;
        }
    };

    private String command;
    private String description;
    private int commandStatus;
    private String wrongParameter;
    protected FileWork fileWork;
    private final int systemCommandStatus = 0;
    private final int fileCommandStatus = 2;
    private final int dirAndFileCommandStatus = 3;
    WorkCommand(int commandStatus, String command, String description, String wrongParameter) {
        this.command = command;
        this.description = description;
        this.commandStatus = commandStatus;
        this.wrongParameter = wrongParameter;
        fileWork = new FileWork();
    }

    protected String getAllCommands() {
        StringBuilder result = new StringBuilder("\nAll commands:\n");
        for (WorkCommand workCommand : WorkCommand.values()) {
            if (!isCommandStatus(workCommand, systemCommandStatus)) {
                result.append("\t").append(workCommand.command).append(" ");
                if (isCommandStatus(workCommand, fileCommandStatus)) {
                    result.append(" <filename> ");
                } else if (isCommandStatus(workCommand, dirAndFileCommandStatus)) {
                    result.append(" <dir or file name> ");
                }
                result.append(" - ").append(workCommand.description)
                        .append("\n");
            }
        }
        return result.toString();
    }

    private boolean isCommandStatus(WorkCommand workCommand, int commandStatus) {
        return workCommand.commandStatus == commandStatus;
    }

    public String getCommandValue() {
        return command;
    }

 }


