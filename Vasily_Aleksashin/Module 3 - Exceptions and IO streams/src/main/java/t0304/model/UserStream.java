package t0304.model;

import t01.exception.ModelException;

public interface UserStream {
	String read(String filePath) throws ModelException;

	void write(String filePath, String message) throws ModelException;
}
