package javase03.common.io;

import javase03.common.exception.*;
import java.io.File;
import java.io.IOException;

/**
 * Files and directories navigation class
 * @author Yury.Vislobodsky
 *
 */
public class FileNavigator {
	public static void createFile(String fileName) throws Exception {
		File file = new File(fileName);
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new MyIOException("Directory " + fileName + " already exists");
			} else {
				throw new MyIOException("File " + fileName + " already exists");
			}
		} else {
			try {
				if (!file.createNewFile()) {
					throw new MyIOException("File  " + fileName + " couldn`t be created!");
				}
			} catch (IOException e) {
				throw new MyIOException(e.getMessage());
			}
		}
	}
		
	public static void appendFile(String fileName, String text) throws Exception {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new MyFileNotFoundException(fileName);
		} else if (file.isDirectory()) {
			throw new MyIOException(fileName + " is a directory!");
		} else {
			String fileText = FileInputOutput.textFileToString(fileName);
			if (fileText == null) {
				fileText = "";
			}
			if (text == null) {
				text = "";
			}
			fileText += text + System.getProperty("line.separator");
			FileInputOutput.stringToTextFile(fileName, fileText);
		}
	}
	
	public static void deleteFile(String fileName) throws Exception {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new MyFileNotFoundException(fileName);
		} else if (file.isDirectory()) {
			throw new MyIOException(fileName + " is a directory!");
		} else if (!file.delete()) {
			throw new MyIOException("File " + fileName + " couldn`t be deleted!");
		}		
	}

	public static void createDirectory(String dirName) throws Exception {
		File dir = new File(dirName);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				throw new MyIOException("Directory " + dirName + " already exists");
			} else {
				throw new MyIOException("File " + dirName + " already exists");
			}
		} else if (!dir.mkdir()) {
			throw new MyIOException("Directory " + dirName + " couldn`t be created!");
		}		
	}	

	public static void deleteDirectory(String dirName) throws Exception {
		File dir = new File(dirName);
		if (!dir.exists()) {
			throw new MyIOException("Directory " + dirName + " not found!");
		} else if (dir.isFile()) {
			throw new MyIOException(dirName + " is a file!");
		} else if (!dir.delete()) {
			throw new MyIOException("Directory " + dirName + " couldn`t be deleted!");
		}		
	}
	
	public static String[] listDirectory(String dirName) throws Exception {
		File dir = new File(dirName);
		if (!dir.exists()) {
			throw new MyIOException("Directory " + dirName + " not found!");
		} else if (dir.isFile()) {
			throw new MyIOException(dirName + " is a file!");
		} else {
			return dir.list();
		}				
	}
}
