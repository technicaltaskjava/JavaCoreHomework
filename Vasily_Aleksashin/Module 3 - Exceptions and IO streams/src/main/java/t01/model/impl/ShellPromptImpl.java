package t01.model.impl;

import t01.model.Environment;
import t01.model.ShellPrompt;

public class ShellPromptImpl implements ShellPrompt {
    public static ShellPrompt instance = null;

    private String prompt;

    private ShellPromptImpl() {
        updatePrompt();
    }

    public static ShellPrompt getInstance() {
        if (instance == null) {
            instance = new ShellPromptImpl();
        }
        return instance;
    }

    @Override
    public String getPrompt() {
        updatePrompt();
        return prompt;
    }

    private void updatePrompt() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(Environment.getUserName())
                .append("@")
                .append(Environment.getHostName())
                .append(" ")
                .append(Environment.getCurrentDir())
                .append(" > ");
        prompt = builder.toString();

    }
}
