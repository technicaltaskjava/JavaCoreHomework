package t01.model.impl;

import t01.model.ShellPrompt;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Calendar;

public class ShellPromptImpl implements ShellPrompt {
	private final String user;
	private final String host;
	private final String home;

	private String currentDir;

	private String prompt;

	public ShellPromptImpl() {
		String hostName;
		this.user = System.getProperty("user.name");
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			hostName = "UNKNOWN";
		}
		this.host = hostName;
		this.home = System.getProperty("user.home");
		this.currentDir = this.home;
		setPrompt(this.user, "@", this.host, " ", this.currentDir, "\n", ">");
	}

	@Override
	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) throws IllegalArgumentException {
		if (currentDir == null) {
			throw new IllegalArgumentException("Path can not be NULL");
		}

		try {
			if (Files.exists(Paths.get(currentDir))) {
                this.currentDir = currentDir;
            }
		} catch (InvalidPathException e) {
			throw new IllegalArgumentException("Path not found");
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
