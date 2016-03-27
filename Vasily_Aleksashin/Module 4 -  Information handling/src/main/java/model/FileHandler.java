package model;

import exeption.FileIOException;
import exeption.ParameterIsNullException;
import model.t02.CrazyLogger;
import util.Validator;

import java.io.*;

public class FileHandler {
	private static CrazyLogger logger = CrazyLogger.getLogger(FileHandler.class);
	private String fileName;

	public String read(final String fileName) throws FileIOException, ParameterIsNullException {
		logger.log(1, "Validate input file name");
		Validator.validate(fileName);
		logger.log(1, "Create file from file name");
		File file = new File(fileName);
		StringBuilder builder = new StringBuilder();
		String cache;
		try (FileReader in = new FileReader(file);
		     BufferedReader reader = new BufferedReader(in)) {
			logger.log(1, "Prepare result");
			while ((cache = reader.readLine()) != null) {
				builder.append(cache).append("\n");
			}
		} catch (IOException e) {
			logger.log(3, String.format("Can not read file %s", fileName));
			throw new FileIOException(String.format("Can not read file %s", fileName));
		}
		logger.log(1, "Return result");
		return builder.toString();
	}

	public void create(final String fileName) throws ParameterIsNullException, FileIOException {
		logger.log(1, "Validate input file name");
		Validator.validate(fileName);
		logger.log(1, "Create file");
		File file = new File(fileName);
		try {
			if (!file.createNewFile()) {
				logger.log(3, String.format("Can not create file %s, the file already exists", fileName));
				throw new FileIOException(String.format("Can not create file %s, the file already exists", fileName));
			}
		} catch (IOException e) {
			logger.log(3, e.getMessage());
			throw new FileIOException(e.getMessage());
		}
	}

	public void write(final String fileName, final String message) throws FileIOException, ParameterIsNullException {
		write(fileName, message, false);
	}

	public void write(final String fileName, final String message, final boolean append) throws ParameterIsNullException, FileIOException {
		logger.log(1, "Validate input file name");
		Validator.validate(fileName);
		logger.log(1, "Validate message");
		Validator.validate(message);
		logger.log(1, "Check file");
		File file = new File(fileName);
		if (!file.isFile() && !file.canWrite()) {
			logger.log(3, String.format("Can not write file %s", fileName));
			throw new FileIOException(String.format("Can not write file %s", fileName));
		}
		logger.log(1, "Write file");
		try (FileWriter out = new FileWriter(fileName, append);
		     BufferedWriter writer = new BufferedWriter(out)) {
			writer.write(message);
		} catch (IOException e) {
			logger.log(3, e.getMessage());
			throw new FileIOException(e.getMessage());
		}
	}

	public BufferedWriter getWriter() throws FileIOException, ParameterIsNullException {
		logger.log(1, "Validate input file name");
		Validator.validate(fileName);
		logger.log(1, "Check file");
		File file = new File(fileName);
		if (!file.isFile() && !file.canWrite()) {
			logger.log(3, String.format("Can not write file %s", fileName));
			throw new FileIOException(String.format("Can not write file %s", fileName));
		}
		FileWriter out;
		BufferedWriter writer;
		try {
			out = new FileWriter(fileName, false);
			writer = new BufferedWriter(out);
			logger.log(1, "Return writer");
			return writer;
		} catch (IOException e) {
			logger.log(3, e.getMessage());
			throw new FileIOException(e.getMessage());
		}
	}

	public void setFileName(final String fileName) {
		logger.log(1, "Set file name");
		this.fileName = fileName;
	}
}
