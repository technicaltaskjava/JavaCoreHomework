package util;

public class Util {
	public static String longTimeToString(final long time) {
		long secs = time / 1000;
		return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60));
	}

	public static long StringTimeToLong(final String time) {
		String[] input = time.split(":");
		if (input.length == 3) {
			return (Integer.parseInt(input[0]) * 3600 + Integer.parseInt(input[1]) * 60 + Integer.parseInt(input[2])) * 1000;
		} else if (input.length == 2) {
			return (Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1])) * 1000;
		}

		return 0;
	}
}
