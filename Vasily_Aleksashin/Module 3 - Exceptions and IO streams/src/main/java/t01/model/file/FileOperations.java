package t01.model.file;

import t01.exception.ModelException;

public interface FileOperations {
	void create(String fileName) throws ModelException;

	void delete(String fileName) throws ModelException;

	String read(String fileName) throws ModelException;

	void write(String fileName, String message, boolean append) throws ModelException;
}
