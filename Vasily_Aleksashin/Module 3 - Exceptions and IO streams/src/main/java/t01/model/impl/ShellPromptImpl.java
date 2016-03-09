package t01.model.impl;

import t01.model.ShellPrompt;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

public class ShellPromptImpl implements ShellPrompt {
	private final String user;
	private final String host;
	private final String home;
	private final String work;
	private final char[] separators = new char[]{'~', '@', '#', '%', '^', '&', '*', '[', ']', '<', '>', '-', '_', '=', ' ', '\n'};

	private String startDir;
	private int count = 1;
	Calendar instance = Calendar.getInstance();

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
		this.work = System.getProperty("user.dir");
		this.startDir = this.home;
		setPrompt(this.user, "@", this.host, " ", this.startDir, "\n", ">");
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
