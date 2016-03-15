package javase03.common.lib;

import java.util.Scanner;

/**
 * Additional class for input methods 
 * @author Yury.Vislobodsky
 *
 */
public class Lib {
	public static int inputInteger(Scanner in) {
		int validInteger = 0;
		boolean error = true;
		while (error) {
			try {
				validInteger = Integer.parseInt(in.next());
				error = false;
			} catch (Exception e) {
				System.out.print("Invalid number! Try again : ");
			}
		}
		return validInteger;
	}
	
	public static int selectContinue(Scanner in) {
		System.out.print("Continue (Y/N) : ");
		if (in.next().toUpperCase().equals("Y")) {
			return 1;
		}
		return 0;
	}
}
