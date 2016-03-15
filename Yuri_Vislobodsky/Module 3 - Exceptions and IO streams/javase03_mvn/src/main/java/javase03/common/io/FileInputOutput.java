package javase03.common.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javase03.common.exception.*;

/**
 * IO file streams class 
 * @author Yury.Vislobodsky
 *
 */
public class FileInputOutput {
	public static String textFileToString(String fileName) throws Exception {
		String text = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String s;
			text = "";
			while ((s = br.readLine()) != null) {
				text += s + "\r\n";
			}
		} catch (FileNotFoundException e) {
			throw new MyFileNotFoundException(fileName);
		} catch (IOException e) {
			throw new MyIOException();
		} finally {
			if (br != null) { 
				br.close();
			}
		}
		return text;
	}
	
	public static void stringToTextFile(String fileName, String text) throws Exception {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(fileName));
			pw.print(text);	
		} catch (IOException e) {
			throw new MyIOException();
		} finally {
			if (pw != null) { 
				pw.close();
			}
		}
	}
	
	public static String binaryFileToString(String fileName) throws Exception {
		String text = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			int bytesAvailable = fis.available();
			byte[] bytes = new byte[bytesAvailable];
			fis.read(bytes, 0, bytesAvailable);			
			text = new String(bytes);
		} catch (FileNotFoundException e) {
			throw new MyFileNotFoundException(fileName);
		} catch (IOException e) {
			throw new MyIOException();
		} finally {
			if (fis != null) { 
				fis.close();
			}
		}
		return text;
	}
	
	public static void stringToBinaryFile(String fileName, String text) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(text.getBytes());
		} catch (IOException e) {
			throw new MyIOException();
		} finally {
			if (fos != null) { 
				fos.close();
			}
		}
	}
}
