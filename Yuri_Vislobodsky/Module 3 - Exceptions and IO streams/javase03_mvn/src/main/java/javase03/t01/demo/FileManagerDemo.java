package javase03.t01.demo;

import java.util.Scanner;
import javase03.common.lib.Lib;
import javase03.common.io.*;
import javase03.common.exception.*;

/**
 * File manager demo 
 * @author Yury.Vislobodsky
 *
 */
public class FileManagerDemo {
	private static Scanner in;
	
	public static String inputString(String caption) {
		System.out.print(caption);
		return in.next();
	}
	
	public static int selectMenuItem() {
		System.out.println();
		System.out.println("MAIN MENU : ");
		System.out.println("1 - Create text file");
		System.out.println("2 - View text file");
		System.out.println("3 - Append text file");		
		System.out.println("4 - Delete text file");
		System.out.println("5 - Create directory");
		System.out.println("6 - View directory");
		System.out.println("7 - Delete directory");
		System.out.println("0 - Quit");
		System.out.print("Your choice : ");
		return Lib.inputInteger(in);		
	}	
		
	public static int selectDoContinue(int selectedItem) {
		if (selectedItem != 0) {
			selectedItem = Lib.selectContinue(in);
		}
		return selectedItem;
	}	
	
	public static void doCreateFile() {
		try {
			String fileName = inputString("Input file name to create : ");
			FileNavigator.createFile(fileName);
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doViewFile() {
		try {
			String fileName = inputString("Input file name to view : ");
			String text = FileInputOutput.textFileToString(fileName);
			if (text != null) {
				String[] strings = text.split("\r\n");
				for (String line : strings) {
					System.out.println(line);
				}
			}
		} catch (MyFileNotFoundException e) {
			System.out.println(e);			
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doAppendFile() {
		try {
			String fileName = inputString("Input file name to append : ");
			String text = inputString("Input text to append : ");
			FileNavigator.appendFile(fileName, text);
		} catch (MyFileNotFoundException e) {
			System.out.println(e);			
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static void doDeleteFile() {
		try {
			String fileName = inputString("Input file name to delete : ");
			FileNavigator.deleteFile(fileName);
		} catch (MyFileNotFoundException e) {
			System.out.println(e);			
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		

	public static void doCreateDirectory() {
		try {
			String dirName = inputString("Input directory name to create : ");
			FileNavigator.createDirectory(dirName);
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}			

	public static void doDeleteDirectory() {
		try {
			String dirName = inputString("Input directory name to delete : ");
			FileNavigator.deleteDirectory(dirName);
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}				

	public static void doViewDirectory() {
		try {
			String dirName = inputString("Input directory name to view : ");
			String[] items = FileNavigator.listDirectory(dirName);
			if (items != null) {
				for (String item : items) {
					System.out.println(item);
				}
			}
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}					
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int selectedItem;
		do {
			selectedItem = selectMenuItem();
			switch (selectedItem) {
			case 1: doCreateFile();
					break;
					
			case 2: doViewFile();
					break;
					
			case 3: doAppendFile();
					break;	
					
			case 4: doDeleteFile();
					break;
					
			case 5: doCreateDirectory();
					break;	
					
			case 6: doViewDirectory();
					break;	
					
			case 7: doDeleteDirectory();
					break;		
			}			
			selectedItem = selectDoContinue(selectedItem);
		} while (selectedItem != 0);
		in.close();
	}
}
