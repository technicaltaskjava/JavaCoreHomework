package t02.util;

import t02.model.exception.FileIOException;
import t02.model.exception.ParameterIsNullException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Utility {
	private static final String REGEX = "\\|";
	private static String[][] inputArray = null;
	private static String delimiter = REGEX;

	private Utility() {
	}

	public static String[][] load(final String fileName) throws FileIOException, ParameterIsNullException {
		return load(fileName, null);
	}

	public static String[][] load(final String fileName, final String regex) throws FileIOException,
			ParameterIsNullException {
		validate(fileName);
		inputArray = null;
		File file = new File(fileName);
		if (!file.isFile() || !file.canRead()) {
			throw new FileIOException("Can not load information from a file");
		}
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				resizeArray();
				if (regex != null) {
					delimiter = regex;
				}
				String line = scanner.nextLine();
				String[] split = line.split(delimiter);
				inputArray[inputArray.length - 1] = split;
			}
		} catch (FileNotFoundException e) {
			throw new FileIOException(String.format("Can not load information from a file: %s", file));
		}
		return inputArray;
	}

	public static void validate(Object object) throws ParameterIsNullException {
		if (object == null) {
			throw new ParameterIsNullException("Parameter can not be NULL");
		}
	}

	public static int stringTimeToInt(final String time) {
		String[] input = time.split(":");
		if (input.length == 3) {
			return (Integer.parseInt(input[0]) * 3600 + Integer.parseInt(input[1]) * 60 + Integer.parseInt(input[2]));
		} else if (input.length == 2) {
			return (Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]));
		}
		return 0;
	}

	private static void resizeArray() {
		if (inputArray == null) {
			inputArray = new String[1][2];
		} else {
			inputArray = Arrays.copyOf(inputArray, inputArray.length + 1);
		}
	}
}
