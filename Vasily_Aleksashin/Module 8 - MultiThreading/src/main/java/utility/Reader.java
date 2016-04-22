package utility;

import exception.FileIOException;
import exception.ParameterIncorrectException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private Reader() {
	}

	public static String read(final String path) throws FileIOException {
		try {
			Validator.isNull(path);
		} catch (ParameterIncorrectException e) {
			throw new FileIOException(e.getMessage(), e);
		}
		File file = new File(path);
		if (!file.canRead()) {
			throw new FileIOException(String.format("Can not read file '%s'", path));
		}
		StringBuilder builder = new StringBuilder();
		try (FileReader out = new FileReader(file);
		     BufferedReader reader = new BufferedReader(out)) {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new FileIOException(e.getMessage(), e);
		}
		return builder.toString();
	}
}
