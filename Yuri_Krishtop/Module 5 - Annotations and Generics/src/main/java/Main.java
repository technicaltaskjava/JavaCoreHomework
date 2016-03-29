import com.epam.task1.SimpleTester;
import com.epam.task2.Pair;
import com.epam.task2.Triplet;
import com.epam.task2.Unit;
import com.epam.task3.CompareUtils;
import com.epam.utils.WriteMenu;

import java.util.Scanner;

/**
 * Created by Yuriy Krishtop on 24.03.2016.
 */
public class Main {

    public static void main(String[] args) {
        String line = "+------------------------------------------------------"+
                "-----------------------------------------------------+";
        System.out.printf("%s%n%-40s %67s%n%s%n", line, "|  Home Work 05: Annotations and Generics", "|", line);
        System.out.printf("%-60s %48s%n", "|  Please, enter an integer number for start:", "|");
        System.out.printf("%-60s %48s%n", "|    1 - Run SimpleTester (need to enable the Java assert)", "|");
        System.out.printf("%-40s %68s%n", "|    2 - Tuples", "|");
        System.out.printf("%-40s %68s%n", "|    3 - Compare Utils", "|");
        System.out.printf("%-40s %68s%n%s%n", "|    4 - close", "|", line);
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int idOperation = input.nextInt();
            switch (idOperation) {
                case 1:
                    System.out.printf("%s%n%-60s %48s%n%s%n", line, "| Hello! This is SimpleTester!", "|", line);
                    System.out.printf("%-60s %48s%n", "| Choose, what do you want:  1 - Run default SimpleTester", "|");
                    System.out.printf("%-17s%50s %41s%n%s%n", "|", "2 - Run SimpleTester for other package", "|", line);
                    Scanner inpt = new Scanner(System.in);
                    if (inpt.hasNextInt()) {
                        switch (inpt.nextInt()) {
                            case 1:
                                SimpleTester tester = new SimpleTester();
                                String[] classesForTest = tester.findClassesForTest();
                                for (String testingClass : classesForTest) {
                                    tester.runTestMethod(testingClass);
                                }
                                System.out.printf("%s%n%-30s%10s%69s%n", line, "| Total classes tested: ",
                                        classesForTest.length, "|");
                                System.out.printf("%-30s%10s%69s%n", "| Total methods tested: ",
                                        tester.getCountTestMethods(), "|");
                                System.out.printf("%-30s%10s%69s%n", "| Total successful tests: ",
                                        tester.getCountSuccessfulTest(), "|");
                                System.out.printf("%-30s%10s%69s%n%s%n", "| Total unsuccessful tests: ",
                                        tester.getCountUnsuccessfulTest(), "|", line);
                                break;
                            case 2:
                                System.out.printf("%s%n%-60s %48s%n%s%n", line,
                                        "| Please, enter package name for testing:", "|", line);
                                Scanner scanin = new Scanner(System.in);
                                String packageName = scanin.nextLine();
                                SimpleTester simplTester = new SimpleTester(packageName);
                                String[] classesForSTest = simplTester.findClassesForTest();
                                for (String testingClass : classesForSTest) {
                                    simplTester.runTestMethod(testingClass);
                                }
                                break;
                            default:
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Command not found, please try again",
                                        "|", line);
                        }
                    } else {
                        System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Please, enter integer in range 1 - 2", "|",
                                line);
                    }
                    break;
                case 2:
                    System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Tuples.", "|", line);
                    System.out.printf("%-40s%67s%n",     "| Choose number of elements for saving:  1", "|");
                    System.out.printf("%-40s%67s%n",     "|                                        2", "|");
                    System.out.printf("%-40s%67s%n%s%n", "|                                        3", "|", line);
                    Scanner inptTup = new Scanner(System.in);
                    if (inptTup.hasNextInt()) {
                        switch (inptTup.nextInt()) {
                            case 1:
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter element: ", "|", line);
                                Unit unit = WriteMenu.adElement();
                                System.out.printf("%s%n%-20s%-10s%30s%49s%n%s%n", line, "| Your element is: ",
                                        unit.getFirst(), unit.getFirst().getClass(), "|", line);
                                break;
                            case 2:
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter first element: ", "|", line);
                                Unit first = WriteMenu.adElement();
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter second element: ", "|", line);
                                Unit second = WriteMenu.adElement();
                                Pair pair = Pair.create(first.getFirst(), second.getFirst());
                                System.out.printf("%s%n%-30s%-20s%30s%29s%n", line, "| Your first element is: ",
                                        pair.getFirst(), pair.getFirst().getClass(), "|");
                                System.out.printf("%-30s%-20s%30s%29s%n%s%n", "| Your second element is: ",
                                        pair.getSecond(), pair.getSecond().getClass(), "|", line);
                                break;
                            case 3:
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter first element: ", "|", line);
                                Unit firstEl = WriteMenu.adElement();
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter second element: ", "|", line);
                                Unit secondEl = WriteMenu.adElement();
                                System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Enter third element: ", "|", line);
                                Unit thirdEl = WriteMenu.adElement();
                                Triplet triplet = Triplet.create(firstEl.getFirst(), secondEl.getFirst(),
                                        thirdEl.getFirst());
                                System.out.printf("%s%n%-30s%-20s%30s%29s%n", line, "| Your first element is: ",
                                        triplet.getFirst(), triplet.getFirst().getClass(), "|");
                                System.out.printf("%-30s%-20s%30s%29s%n", "| Your second element is: ",
                                        triplet.getSecond(), triplet.getSecond().getClass(), "|");
                                System.out.printf("%-30s%-20s%30s%29s%n%s%n", "| Your third element is: ",
                                        triplet.getThird(), triplet.getThird().getClass(), "|", line);
                                break;
                            default:
                                System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Command not found, please try again",
                                        "|", line);
                        }
                    } else {
                        System.out.printf("%s%n%-40s %68s%n%s%n", line, "| Please, enter integer in range 1 - 3", "|",
                                line);
                    }
                    break;
                case 3:
                    System.out.printf("%s%n%-40s %65s%n%s%n", line, "| Please, enter count of elements in array:", "|",
                            line);
                    Scanner inptCount = new Scanner(System.in);
                    if (inptCount.hasNextInt()) {
                        int countEl = inptCount.nextInt();
                        System.out.printf("%s%n%-40s%67s%n", line, "| Please, enter type of array:  1- Integer", "|");
                        System.out.printf("%-40s%67s%n", "|                               2 - Double", "|");
                        System.out.printf("%-40s%67s%n%s%n", "|                               3 - String", "|", line);
                        Scanner inptType = new Scanner(System.in);
                        if (inptType.hasNextInt()) {
                            switch (inptType.nextInt()) {
                                case 1:
                                    Integer[] arrInteger = new Integer[countEl];
                                    for (int i = 0; i < countEl; i++) {
                                        System.out.printf("%s%n%-20s %-10s %-30s %46s%n%s%n", line, "| Please, enter",
                                                i + 1, "- element in array:", "|", line);
                                        Scanner inputInt = new Scanner(System.in);
                                        if (inputInt.hasNextInt()) {
                                            arrInteger[i] = inputInt.nextInt();
                                        } else {
                                            System.out.printf("%s%n%-40s%69s%n%s%n", line,
                                                    "| Sorry, you have to enter integer.", "|", line);
                                        }
                                    }
                                    System.out.printf("%s%n%-40s %-10s %57s%n", line, "| Min element of array: ",
                                            CompareUtils.min(arrInteger), "|");
                                    System.out.printf("%-40s %-10s %57s%n", "| Max element of array: ",
                                            CompareUtils.max(arrInteger), "|");
                                    System.out.printf("%-40s %-10s %57s%n%s%n", "| Median element of array: ",
                                            CompareUtils.median(arrInteger), "|", line);
                                    break;
                                case 2:
                                    Double[] arrDouble = new Double[countEl];
                                    for (int i = 0; i < countEl; i++) {
                                        System.out.printf("%s%n%-20s %-10s %-30s %46s%n%s%n", line, "| Please, enter",
                                                i + 1, "- element in array:", "|", line);
                                        Scanner inputDouble = new Scanner(System.in);
                                        if (inputDouble.hasNextDouble()) {
                                            arrDouble[i] = inputDouble.nextDouble();
                                        } else {
                                            System.out.printf("%s%n%-40s%69s%n%s%n", line,
                                                    "| Sorry, you have to enter double.", "|", line);
                                        }
                                    }
                                    System.out.printf("%s%n%-40s %-10s %57s%n", line, "| Min element of array: ",
                                            CompareUtils.min(arrDouble), "|");
                                    System.out.printf("%-40s %-10s %57s%n", "| Max element of array: ",
                                            CompareUtils.max(arrDouble), "|");
                                    System.out.printf("%-40s %-10s %57s%n%s%n", "| Median element of array: ",
                                            CompareUtils.median(arrDouble), "|", line);
                                    break;
                                case 3:
                                    String[] arrStr = new String[countEl];
                                    for (int i = 0; i < countEl; i++) {
                                        System.out.printf("%s%n%-20s %-10s %-30s %46s%n%s%n", line, "| Please, enter",
                                                i + 1, "- element in array:", "|", line);
                                        Scanner inputStr = new Scanner(System.in);
                                        if (inputStr.hasNextLine()) {
                                            arrStr[i] = inputStr.nextLine();
                                        } else {
                                            System.out.printf("%s%n%-40s%69s%n%s%n", line,
                                                    "| Sorry, you have to enter string", "|", line);
                                        }
                                    }
                                    System.out.printf("%s%n%-40s %-10s %57s%n", line, "| Min element of array: ",
                                            CompareUtils.min(arrStr), "|");
                                    System.out.printf("%-40s %-10s %57s%n", "| Max element of array: ",
                                            CompareUtils.max(arrStr), "|");
                                    System.out.printf("%-40s %-10s %57s%n %s%n", "| Median element of array: ",
                                            CompareUtils.median(arrStr), "|", line);
                                    break;
                                default:
                                    System.out.printf("%s%n%-40s%69s%n%s%n", line,
                                            "| Command not found, please try again", "|", line);
                            }
                        } else {
                            System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Sorry, you have to enter integer.",
                                    "|", line);
                        }
                    } else {
                        System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Sorry, you have to enter integer.", "|",
                                line);
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Command not found, please try again", "|", line);
            }
        } else {
            System.out.printf("%s%n%-40s%69s%n%s%n", line, "| Sorry, you have to enter integer.", "|", line);
        }
    }
}