package model.task5;

import java.util.Map;

public class TableFormatter {
	private static final String FUNCTIONALITY = "functionality";
	private static final String TYPICAL_USAGE = "typicalUsage";
	private static final String HEADER_INTERFACE = "Interface";
	private static final String HEADER_FUNC = "Basic functionality";
	private static final String HEADER_USAGE = "Examples of typical use";
	private static final String DELIMITER = "|";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String REGEX = "%-10s";
	private static final int SIZE = 75;

	private static StringBuilder builder = null;
	private static int indexFunc = 0;
	private static int indexUsage = 0;

	private TableFormatter() {
	}

	public static String getHeader() {
		builder = new StringBuilder();
		String row = "%-" + SIZE + "s";
		builder.append(DELIMITER).append(String.format(REGEX, HEADER_INTERFACE));
		builder.append(DELIMITER).append(String.format(row, HEADER_FUNC));
		builder.append(DELIMITER).append(String.format(row, HEADER_USAGE));
		builder.append(DELIMITER).append("\n");
		return builder.toString();
	}

	public static String getTable(final Map<String, String> description, final Interface key, boolean flag) {
		if (!flag) {
			builder = new StringBuilder();
		}
		String firstCol = String.format(REGEX, !flag ? key.getName() : EMPTY);
		builder.append(DELIMITER).append(firstCol).append(DELIMITER);

		String row = "%-" + SIZE + "s";
		String keyFunc = key.getName() + "." + FUNCTIONALITY;
		String valueFunc = description.get(keyFunc);
		String[] splitValueFunc = valueFunc.split(SPACE);
		String secondCol = String.format(row, getCell(splitValueFunc, true));
		builder.append(secondCol).append(DELIMITER);

		String keyUsage = key.getName() + "." + TYPICAL_USAGE;
		String valueUsage = description.get(keyUsage);
		String[] splitValueUsage = valueUsage.split(SPACE);
		String thirdCol = String.format(row, getCell(splitValueUsage, false));
		builder.append(thirdCol).append(DELIMITER);

		int maxIndex = Math.max(splitValueFunc.length, splitValueUsage.length);
		builder.append("\n");
		if (Math.max(indexFunc, indexUsage) < maxIndex - 1) {
			indexFunc++;
			indexUsage++;
			getTable(description, key, true);
		}
		return builder.toString();
	}

	public static void reset() {
		indexFunc = 0;
		indexUsage = 0;
	}

	private static String getCell(final String[] splitValue, final boolean column) {
		StringBuilder builder = new StringBuilder();
		int length = 0;
		int startIndex = column ? TableFormatter.indexFunc : TableFormatter.indexUsage;
		for (int index = startIndex; index < splitValue.length; index++) {
			length += splitValue[index].length() + 1;
			if (length <= SIZE) {
				builder.append(splitValue[index]).append(SPACE);
				if (column) {
					TableFormatter.indexFunc = index;
				} else {
					TableFormatter.indexUsage = index;
				}
			} else {
				break;
			}
		}
		return builder.toString();
	}
}
