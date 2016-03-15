package javase03.t04.demo;

import java.util.Scanner;
import javase03.common.exception.*;
import javase03.common.io.FileInputOutput;
import javase03.t04.keywords.*;

/**
 * Working with java keywords with text streams (demo class) 
 * @author Yury.Vislobodsky
 *
 */
public class JavaKeywordsTextDemo {
	public static String sourceFile;
	public static String reportFile;
	
	public static void inputFiles() {
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			System.out.print("Input java file name : ");
			sourceFile = in.next();
			System.out.print("Input report file name : ");
			reportFile = in.next();
		} finally {
			in.close();
		}		
	}
	
	public static void main(String[] args) {
		inputFiles();				
		try {
			String text = FileInputOutput.textFileToString(sourceFile);
			JavaKeywords javaKeywords = new JavaKeywords();
			FileInputOutput.stringToTextFile(reportFile, 
												javaKeywords.getResult(text));
			System.out.print("Done successfully!");
		} catch (MyFileNotFoundException e) {
			System.out.println(e);			
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
