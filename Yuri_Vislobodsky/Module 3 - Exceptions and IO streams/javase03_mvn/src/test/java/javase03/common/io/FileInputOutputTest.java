package javase03.common.io;

import junit.framework.Test;
import junit.framework.TestCase;

public class FileInputOutputTest extends TestCase {
	public void testInputOutput() throws Exception {
		String text = "Hello, world!";
		String fileText = "test.txt";
		String fileBin = "test.bin";
		FileInputOutput.stringToTextFile(fileText, text);
		String result = FileInputOutput.textFileToString(fileText);
		FileInputOutput.stringToBinaryFile(fileBin, result);
		result = FileInputOutput.textFileToString(fileBin).split(System.getProperty("line.separator"))[0];
		assertEquals(text, result);
	}
}