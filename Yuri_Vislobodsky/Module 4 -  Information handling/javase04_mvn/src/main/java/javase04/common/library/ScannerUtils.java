package javase04.common.library;

import java.util.Scanner;

/**
 * Class with some additional utilites for Scanner input
 * Created by Yury Vislobodsky on 18.03.2016.
 */
public class ScannerUtils {
    public static int tryToInputInteger(Scanner in, String caption) {
        int validInteger = 0;
        boolean error = true;
        System.out.print(caption);
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

    public static int tryToContinue(Scanner in, String caption) {
        System.out.print(caption + " (Y/N) : ");
        if (in.next().toUpperCase().equals("Y")) {
            return 1;
        }
        return 0;
    }

    public static String tryToInputString(Scanner in, String caption) {
        System.out.print(caption);
        return in.next();
    }
}
