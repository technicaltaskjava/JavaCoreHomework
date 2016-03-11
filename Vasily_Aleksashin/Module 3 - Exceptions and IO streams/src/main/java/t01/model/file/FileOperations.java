package t01.model.file;

import t01.exception.ModelException;

public interface FileOperations {
	void create(String fileName) throws ModelException;

	void delete(String fileName) throws ModelException;
}
