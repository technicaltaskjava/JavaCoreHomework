package model.t02;

import exeption.ParameterIsNullException;
import util.Constant;
import util.Validator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

public class CrazyLogger {
	public static CrazyLogger logger = null;
	private static StringBuilder memBuilder = new StringBuilder();
	private SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
	private String className = null;

	private CrazyLogger(final Class<?> clazz) {
		if (clazz != null) {
			className = clazz.getCanonicalName();
		}
	}

	public static CrazyLogger getLogger(final Class<?> clazz) {
		logger = new CrazyLogger(clazz);
		return logger;
	}

	public static CrazyLogger getLogger() {
		return getLogger(null);
	}

	public void log(final String message) {
		log(Level.INFO, message);
	}

	public void log(final int level, final String message) {
		log(Level.getLevel(level), message);
	}

	public void log(final Level level, final String message) {
		memBuilder
				.append(dateFormat.format(System.currentTimeMillis()))
				.append(getLevel(level))
				.append(getClassName())
				.append(" - ");
		if (message == null || message.equals("")) {
			memBuilder.append(Constant.NO_MESSAGE);
		} else {
			memBuilder.append(message);
		}
		memBuilder.append('\n');
	}

	public void print() {
		System.out.println(memBuilder.toString());
	}

	public void print(final OutputStream outputStream) throws IOException, ParameterIsNullException {
		Validator.validate(outputStream);
		outputStream.write(memBuilder.toString().getBytes());
	}

	public void print(final Writer writer) throws IOException, ParameterIsNullException {
		Validator.validate(writer);
		writer.write(memBuilder.toString());
	}

	public String printString() {
		return memBuilder.toString();
	}

	public void clear() {
		memBuilder.setLength(0);
	}

	public String findLog(final long date) throws ParameterIsNullException {
		return findLog(new Date(date));
	}

	public String findLog(final Date date) throws ParameterIsNullException {
		Validator.validate(date);
		StringBuilder result = new StringBuilder();
		String timeStamp = dateFormat.format(date);
		Matcher matcher = Constant.PATTERN_MESSAGE_FORMAT.matcher(memBuilder);
		while (matcher.find()) {
			if (matcher.group(2).contains(timeStamp)) {
				result.append(memBuilder, matcher.start(), matcher.end()).append("\n");
			}
		}
		return result.toString();
	}

	public String findLog(final Date start, final Date end) throws ParameterIsNullException {
		Validator.validate(start);
		Validator.validate(end);
		long startTime = start.getTime();
		long endTime = end.getTime();
		if (startTime > endTime) {
			return findTime(endTime, startTime);
		}
		if (startTime == endTime) {
			return findLog(startTime);
		}
		return findTime(startTime, endTime);
	}

	public String findLog(final long start, final long end) throws ParameterIsNullException {
		if (start > end) {
			return findTime(end, start);
		}
		if (start == end) {
			return findLog(start);
		}
		return findTime(start, end);
	}

	public String findLog(final String... message) throws ParameterIsNullException {
		Validator.validate(message);
		StringBuilder result = new StringBuilder();
		Matcher matcher = Constant.PATTERN_MESSAGE_FORMAT.matcher(memBuilder);
		while (matcher.find()) {
			boolean flag = true;
			for (String element : message) {
				if (!matcher.group(3).contains(element)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result.append(matcher.group(1)).append("\n");
			}
		}
		return result.toString();
	}

	@Override
	public String toString() {
		return memBuilder.toString();
	}

	void setLog(final long time, final String msg) {
		memBuilder
				.append(dateFormat.format(new Date(time)))
				.append(getClassName())
				.append(" - ")
				.append(msg)
				.append('\n');
	}

	private String findTime(final long start, final long end) {
		StringBuilder result = new StringBuilder();
		Matcher matcher = Constant.PATTERN_MESSAGE_FORMAT.matcher(memBuilder);
		while (matcher.find()) {
			long parse;
			try {
				String group = matcher.group(2);
				dateFormat.setLenient(false);
				parse = dateFormat.parse(group).getTime();
				if (parse >= start && parse <= end) {
					result.append(matcher.group(1)).append("\n");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	private String getClassName() {
		if (className != null) {
			return String.format(" - %s", className);
		}
		return "";
	}

	private String getLevel(final Level level) {
		if (level != null) {
			return String.format(" - [%-5s]", level);
		}
		return "";
	}

}
