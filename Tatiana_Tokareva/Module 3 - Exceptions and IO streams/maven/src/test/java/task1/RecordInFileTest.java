package task1;


import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import task1.RecordInFile;

public class RecordInFileTest {
	private static final String FILE_NAME = "src\\test\\resources\\test_file.txt";
	private static final String TEXT = "some text";
@Test
	
	public void testWrite()throws FileNotFoundException {

		RecordInFile record= new RecordInFile();
		
		Assert.assertTrue(record.write(FILE_NAME,TEXT,false));
	}

public void testAppend()throws FileNotFoundException {

	RecordInFile record= new RecordInFile();
	
	Assert.assertTrue(record.write(FILE_NAME,TEXT,true));
}

@Test

	public void testDeleteFile() {

		RecordInFile record= new RecordInFile();	
		Assert.assertTrue(record.delete(FILE_NAME));
	}
}
