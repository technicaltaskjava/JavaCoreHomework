package t01.model;

import t01.exception.ModelException;

import java.nio.file.Path;

public interface DirectoryInfo {
	Path[] getDirStream(String path) throws ModelException;

	String getDirInfo(Path[] paths) throws ModelException;
}
