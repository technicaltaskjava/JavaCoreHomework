package t01.controller;

import t01.exception.RunnerException;
import t01.exception.TargetClassPathNotFoundException;
import t01.model.Runner;

import java.io.File;
import java.util.Arrays;

class TestController {
	private static final String REGEX = "[\\u005c/]";
	private final Runner runner = new Runner();
	private String[] fileClassName = null;
	private String mainClassTestPath;
	private String parentClassTestPath;

	String run(final String targetClassPath) throws TargetClassPathNotFoundException {
		File file = new File(targetClassPath);
		if (!file.exists()) {
			throw new TargetClassPathNotFoundException(String.format("Target path with class files for testing not found in %s", file.getAbsolutePath()));
		}
		mainClassTestPath = file.getAbsolutePath();
		parentClassTestPath = file.getName();
		getClassName(file);
		StringBuilder builder = new StringBuilder();
		try {
			for (String className : fileClassName) {
				builder.append(runner.run(className)).append("\n");
			}
		} catch (RunnerException e) {
			return e.getMessage();
		}
		return builder.toString();
	}

	private void getClassName(final File file) {
		String[] listFile = file.list();
		for (String f : listFile) {
			File innerFile = new File(file.getAbsolutePath() + File.separator + f);
			if (innerFile.isDirectory()) {
				getClassName(innerFile);
			}
			if (f.endsWith(".class")) {
				resizeArray();
				String path = file.getAbsolutePath();
				String parent = path.substring(mainClassTestPath.length(), path.length());
				String fileName = f.substring(0, f.length() - 6);
				fileName = fileName.replaceAll(REGEX, ".");
				parent = parent.replaceAll(REGEX, ".");
				String className = parentClassTestPath + parent + "." + fileName;
				fileClassName[fileClassName.length - 1] = className;
			}
		}
	}

	private void resizeArray() {
		if (fileClassName == null) {
			fileClassName = new String[1];
		} else {
			fileClassName = Arrays.copyOf(fileClassName, fileClassName.length + 1);
		}
	}
}
