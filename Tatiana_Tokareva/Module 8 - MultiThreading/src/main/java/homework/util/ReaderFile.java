package homework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderFile {
	private static final Logger log = LoggerFactory.getLogger(ReaderFile.class);

	private ReaderFile() {
	}

	public static String read(String fileName) {
		try (FileReader reader = new FileReader(fileName);
		     BufferedReader bufferedReader = new BufferedReader(reader)) {
			StringBuilder builder = new StringBuilder();
			String output;
			while ((output = bufferedReader.readLine()) != null) {
				builder.append(output).append("\n");
			}
			return builder.toString();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
