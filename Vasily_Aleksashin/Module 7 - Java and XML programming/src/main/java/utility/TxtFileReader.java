package utility;

import exception.FileIOException;
import exception.ParameterIsNullException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtFileReader {
	private TxtFileReader() {
	}

	public static String read(final String fileName) throws FileIOException {
		StringBuilder builder = new StringBuilder();
		try {
			Validator.isNull(fileName);
			File file = new File(fileName);
			if (!file.canRead()) {
				throw new FileIOException(String.format("Can not read file '%s'", file));
			}
			try (FileReader out = new FileReader(file);
			     BufferedReader reader = new BufferedReader(out)) {
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line).append("\n");
				}
			}
		} catch (ParameterIsNullException | IOException e) {
			throw new FileIOException(e.getMessage(), e);
		}
		return builder.toString();
	}
}
