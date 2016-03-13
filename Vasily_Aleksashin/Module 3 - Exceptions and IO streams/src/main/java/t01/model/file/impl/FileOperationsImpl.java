package t01.model.file.impl;

import t01.exception.ModelException;
import t01.model.file.FileOperations;

import java.io.*;

public class FileOperationsImpl implements FileOperations {
	private File file;

	@Override
	public void create(final String fileName) throws ModelException {
		validateParameter(fileName);

		file =  new File(fileName);
		try {
			if (!file.createNewFile()) {
				throw new ModelException(String.format("Can not create file %s, the file already exists", fileName));
			}
		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void delete(final String fileName) throws ModelException {
		validateParameter(fileName);

		file = new File(fileName);
		if (!validateFile(file) || !file.delete()) {
			throw new ModelException(String.format("Can not delete file %s", fileName));
		}
	}

	@Override
	public String read(final String fileName) throws ModelException {
		validateParameter(fileName);
		file = new File(fileName);
		/*if (!file.isFile() || !file.canRead()) {
			throw new ModelException(String.format("Can not read file %s", fileName));
		}*/
		StringBuilder builder = new StringBuilder();
		String cache;
		try (FileReader in = new FileReader(file);
		     BufferedReader reader = new BufferedReader(in)) {
			while ((cache = reader.readLine()) != null) {
				builder.append(cache).append("\n");
			}
		} catch (FileNotFoundException e) {
			throw new ModelException(String.format("File not found: %s", fileName), e);
		} catch (IOException e) {
			throw new ModelException(String.format("Can not read file %s", fileName), e);
		}

		return builder.toString();
	}

	@Override
	public void write(final String fileName, final String message, final boolean append) throws ModelException {
		validateParameter(fileName);
		validateParameter(message);
		file = new File(fileName);
		if (!validateFile(file)) {
			throw new ModelException(String.format("Can not write file %s", fileName));
		}
		try (FileWriter out = new FileWriter(fileName, append);
		     BufferedWriter writer = new BufferedWriter(out)) {
			writer.write(message);
		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}
	}

	private void validateParameter(final String fileName) throws ModelException {
		if (fileName == null || fileName.equals("")) {
			throw new ModelException("Parameter incorrect");
		}
	}

	private boolean validateFile(final File file) {
		return file.isFile() && file.canWrite();
	}


}
