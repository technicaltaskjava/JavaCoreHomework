package cafe;

import cuisine.Nation;
import cuisine.NationalCuisine;
import report.ReportMaker;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 *         Pattern singleton: Used for writing tasks
 *         Pattern abstract factory: Used to create ethnic cuisine food
 */
public class SomeCafe {
    private static final String SELECT_CUISINE_MESSAGE = "Select national cuisine";
    private static PrintStream out = System.out;
    private static NationalCuisine cuisine = null;

    private SomeCafe() {
    }

    public static void printMenu() {
        out.println("\n1] Select Mexican cuisine");
        out.println("2] Select Japanese cuisine");
        out.println("3] Cook first dishes");
        out.println("4] Cook second dishes");
        out.println("5] Cook snack");
        out.println("6] View cooking tasks");
        out.println("0] Exit");
    }

    public static void printTaskList() {
        ReportMaker reportMaker = ReportMaker.getInstance();
        if (reportMaker.taskCount() == 0) {
            out.println("Tasks list is empty");
        } else {
            for (String task : reportMaker.getTaskList()) {
                out.println(task);
            }
        }
    }

    public static void cookFirstDishesItem() {
        if (cuisine == null) {
            out.println(SELECT_CUISINE_MESSAGE);
        } else {
            cuisine.cookFirstDishes();
        }
    }

    public static void cookSecondDishesItem() {
        if (cuisine == null) {
            out.println(SELECT_CUISINE_MESSAGE);
        } else {
            cuisine.cookSecondDishes();
        }
    }

    public static void cookSnacksItem() {
        if (cuisine == null) {
            out.println(SELECT_CUISINE_MESSAGE);
        } else {
            cuisine.cookSnacks();
        }
    }

    public static void main(String[] args) {
        int userChoice = -1;
        Scanner scanner = new Scanner(System.in);

        while (userChoice != 0) {
            try {
                printMenu();

                out.print("Input you choice ");
                userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 0:
                        break;
                    case 1:
                        cuisine = new NationalCuisine(Nation.MEXICO);
                        break;
                    case 2:
                        cuisine = new NationalCuisine(Nation.JAPANESE);
                        break;
                    case 3:
                        cookFirstDishesItem();
                        break;
                    case 4:
                        cookSecondDishesItem();
                        break;
                    case 5:
                        cookSnacksItem();
                        break;
                    case 6:
                        printTaskList();
                        break;
                    default:
                        out.println("Unknown menu item");
                        break;
                }
            } catch (NoSuchElementException e) {
                out.println(e);
                userChoice = -1;
            }

        }
    }
}
