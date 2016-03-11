package t01.model.file.impl;

import t01.exception.ModelException;
import t01.model.file.FileOperations;

import java.io.File;
import java.io.IOException;

public class FileOperationsImpl implements FileOperations {
	@Override
	public void create(final String fileName) throws ModelException {
		validateFileName(fileName);

		File file = new File(fileName);
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
		validateFileName(fileName);

		File file = new File(fileName);
		if (!file.delete()) {
			throw new ModelException(String.format("Can not delete file %s", fileName));
		}
	}

	private void validateFileName(final String fileName) throws ModelException {
		if (fileName == null || fileName.equals("")) {
			throw new ModelException("File name incorrect");
		}
	}
}
