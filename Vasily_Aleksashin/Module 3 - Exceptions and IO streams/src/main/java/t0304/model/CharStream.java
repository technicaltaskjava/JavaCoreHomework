package t0304.model;

import t01.exception.ModelException;

import java.io.*;

public class CharStream implements UserStream {
	@Override
	public String read(final String filePath) throws ModelException {
		validate(filePath);
		File file = new File(filePath);
		String cache;
		try (FileReader in = new FileReader(file);
		     BufferedReader reader = new BufferedReader(in)) {
			char[] inputByte = new char[(int) file.length()];
			reader.read(inputByte);
			cache = new String(inputByte);
		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}
		return cache;
	}

	@Override
	public void write(final String filePath, final String message) throws ModelException {
		validate(filePath);
		validate(message);
		File file = new File(filePath);
		try (FileWriter out = new FileWriter(file);
		     BufferedWriter writer = new BufferedWriter(out)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			writer.write(message);
			writer.flush();
		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}
	}

	private void validate(final String filePath) throws ModelException {
		if (filePath == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}
}
