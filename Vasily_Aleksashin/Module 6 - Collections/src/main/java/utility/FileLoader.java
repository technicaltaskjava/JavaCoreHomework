package utility;

import exception.FileIOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
	private static final String DEFAULT_FILE_NAME = "src/main/resources/aircrafts.txt";

	private FileLoader() {
	}

	public static String loadFromTxt() throws FileIOException {
		return loadFromTxt(DEFAULT_FILE_NAME);
	}

	public static String loadFromTxt(final String fileName) throws FileIOException {
		final String pathname = fileName == null ? DEFAULT_FILE_NAME : fileName;
		File file = new File(pathname);
		if (!file.canRead()) {
			throw new FileIOException(String.format("Can not read file '%s'", file.getName()));
		}
		StringBuilder builder = new StringBuilder();
		try (FileReader out = new FileReader(file);
		     BufferedReader reader = new BufferedReader(out)) {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new FileIOException(String.format("Can not read file '%s'", file.getName()), e);
		}
		return builder.deleteCharAt(builder.length() - 1).toString();
	}
}
