package t01.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Environment {

	public static String getUserName() {
		return System.getProperty("user.name");
	}

	public static String getHostName() {
		String host;
		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			host = "UNKNOWN";
		}
		return host;
	}

	public static String getHomeDir() {
		return System.getProperty("user.home");
	}
}
