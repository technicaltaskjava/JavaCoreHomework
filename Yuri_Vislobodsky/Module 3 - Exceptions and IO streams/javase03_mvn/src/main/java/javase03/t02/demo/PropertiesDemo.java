package javase03.t02.demo;

import javase03.common.exception.*;
import javase03.common.io.FileInputOutput;
import javase03.t02.properties.*;
import java.util.Scanner;

/**
 * Working with .properties (demo class) 
 * @author Yury.Vislobodsky
 *
 */
public class PropertiesDemo {
	public static void prepareDemoProperties() {
		String text = new String("Param_1 = 35\r\n" +
									"Param_2 = UserName098\r\n" +
									"Param_3 = I like Java:)");
		try {
			FileInputOutput.stringToTextFile("demo.properties", text);
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// write prepared property file "demo.properties"
		prepareDemoProperties();
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			System.out.print("Input file name : ");
			Properties properties = new Properties(FileInputOutput.textFileToString(in.next()));	
			do {
				System.out.print("Input property name : ");
				try {
					System.out.println("Property value : " + 
										properties.getPropertyValue(in.next()));
				} catch (MyPropertyKeyNotFoundException e) {
					System.out.println(e);
				}	
				System.out.print("Continue (Y/N) : ");
			} while (in.next().toUpperCase().equals("Y"));				
		} catch (MyFileNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			in.close();
		}	
	}
}
