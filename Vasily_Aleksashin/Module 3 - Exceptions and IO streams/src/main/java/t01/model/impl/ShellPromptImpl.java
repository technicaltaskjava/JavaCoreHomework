package t01.model.impl;

import t01.exception.ModelException;
import t01.model.Environment;
import t01.model.ShellPrompt;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class ShellPromptImpl implements ShellPrompt {
	public static ShellPrompt instance = null;

	private String currentDir;
	private String prompt;

	private ShellPromptImpl() {
		currentDir = Environment.getHomeDir();
		setPrompt(Environment.getUserName(), "@", Environment.getHostName(), " ", currentDir, "\n", ">");
	}

	public static ShellPrompt getInstance() {
		if (instance == null) {
			instance = new ShellPromptImpl();
		}
		return instance;
	}

	@Override
	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String path) throws ModelException {
		if (path == null) {
			throw new ModelException("Path can not be NULL");
		}

		try {
			if (Files.exists(Paths.get(path))) {
				this.currentDir = path;
			}
		} catch (InvalidPathException e) {
			throw new ModelException(String.format("Path: '%s' not found", path));
		}
	}

	@Override
	public String getPrompt() {
		return prompt;
	}

	private void setPrompt(String... args) {
		if (args != null && args.length > 0) {
			StringBuilder builder = new StringBuilder();
			for (String arg : args) {
				builder.append(arg);
			}
			builder.append(" ");
			prompt = builder.toString();
		}
	}
}
