package Task4;

import java.util.Scanner;

/**
 * Created by Oleg on 15.03.2016.
 */
public class WorkWithConsoleInput {

    public static String enterPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path:");
        return scanner.nextLine();
    }

}
