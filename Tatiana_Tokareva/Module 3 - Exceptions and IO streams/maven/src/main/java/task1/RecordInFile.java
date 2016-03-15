package task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class RecordInFile {
	
	
	
	public boolean write(String fileName, String text, boolean append) throws FileNotFoundException {
		File file = new File(fileName);
		try (FileWriter out = new FileWriter(file.getAbsoluteFile(), append);
		     BufferedWriter writer = new BufferedWriter(out)) {
			file.createNewFile();
			writer.write(text);
			return true;
		} catch (IOException e) {
			System.out.println("Write to file is fail");
		}
		return false;
	}

	public boolean write(String fileName, String text) throws FileNotFoundException {
		return write(fileName, text, true);
	}

	public boolean delete(String fileName) {
		if (!new File(fileName).delete()) {
			System.out.println("Delete failed");
			return false;
		} else {
			System.out.println("file deleted");
			return true;
		}
	}

	public boolean append(String fileName, String text) throws FileNotFoundException {
		return write(fileName, text, true);
	}
}
	
	
	