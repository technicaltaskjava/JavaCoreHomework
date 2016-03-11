package t01.model.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;
import t01.model.file.impl.FileOperationsImpl;

import java.io.File;

import static org.junit.Assert.*;

public class FileOperationsTest {
	private FileOperations fileOperations;

	@Before
	public void setup() {
		fileOperations = new FileOperationsImpl();
	}
	@Test (expected = ModelException.class)
	public void testCreateFileWithFileNameIsNull() throws ModelException {
		fileOperations.create(null);
	}

	@Test (expected = ModelException.class)
	public void testCreateFileWithEmptyFileName() throws ModelException {
		String fileName = "";
		fileOperations.create(fileName);
	}

	@Test
	public void testCreateFileWithExist() throws ModelException {
		String fileName = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
				"Module 3 - Exceptions and IO streams\\src\\test\\resources\\test.txt";
		File file = new File(fileName);
		try {
			if (file.exists()) {
				fileOperations.create(fileName);
				fail();
			}
		} catch (ModelException e) {
			//test done
		}
	}

	@Test
	public void testCreateFileWithNotExist() throws ModelException {
		String fileName = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
				"Module 3 - Exceptions and IO streams\\src\\test\\resources\\test.txt";
		File file = new File(fileName);
		if (!file.exists()) {
			fileOperations.create(fileName);
		}
	}

	@Test (expected = ModelException.class)
	public void testDeleteFileWithFileNameIsNull() throws ModelException {
		fileOperations.delete(null);
	}

	@Test (expected = ModelException.class)
	public void testDeleteFileWithEmptyFileName() throws ModelException {
		String fileName = "";
		fileOperations.delete(fileName);
	}

	@Test
	public void testDeleteFileWithNotExist() throws ModelException {
		String fileName = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
				"Module 3 - Exceptions and IO streams\\src\\test\\resources\\test.txt";
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				fileOperations.delete(fileName);
				fail();
			}
		} catch (ModelException e) {
			//test done
		}
	}

	@Test
	public void testDeleteFileWithExist() throws ModelException {
		String fileName = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
				"Module 3 - Exceptions and IO streams\\src\\test\\resources\\test.txt";
		File file = new File(fileName);
		if (file.exists()) {
			fileOperations.delete(fileName);
		}
	}
}
