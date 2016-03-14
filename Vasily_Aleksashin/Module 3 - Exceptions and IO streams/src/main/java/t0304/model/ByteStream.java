package t0304.model;

import t01.exception.ModelException;

import java.io.*;

public class ByteStream implements UserStream {

	@Override
	public String read(final String filePath) throws ModelException {
		validate(filePath);
		File file = new File(filePath);
		String cache;
		try (FileInputStream reader = new FileInputStream(file)) {
			byte[] inputByte = new byte[(int) file.length()];
			reader.read(inputByte);
			cache = new String(inputByte, "utf-8");
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
		try (FileOutputStream writer = new FileOutputStream(file)){
			if (!file.exists()) {
				file.createNewFile();
			}
			writer.write(message.getBytes());
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
