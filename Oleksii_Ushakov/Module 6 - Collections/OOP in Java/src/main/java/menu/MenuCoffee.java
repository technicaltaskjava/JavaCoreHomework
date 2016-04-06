package menu;

import coffee.CoffeePacking;
import coffee.factory.CoffeePackingFactory;
import transporter.Van;

import java.io.PrintStream;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */
public class MenuCoffee {//NOSONAR
    private static PrintStream output = System.out;//NOSONAR
    private static Scanner scanner = new Scanner(System.in);
    private static Van transporter;

    public static void fillTransporterRandomly() {//NOSONAR
        CoffeePacking packing;

        while (true) {
            packing = CoffeePackingFactory.getCustomCoffeePacking(CoffeePackingFactory.getCustomType());

            if (transporter.canAddCoffeePacking(packing)) {
                transporter.addCoffeePacking(packing);
            } else {
                break;
            }
        }
    }

    public static void viewMenu() {//NOSONAR
        output.println("\n1 - View transporter coffee list");
        output.println("2 - Sort coffee list by price");
        output.println("3 - Search coffee by weight");
        output.println("0 - Exit");
    }

    public static void drawRepetitiveSymbol(char symbol, int times) {
        for (int i = 0; i < times; i++) {
            output.print(symbol);
        }
        output.println();
    }

    public static void viewCoffeeList(List<CoffeePacking> list) {//NOSONAR
        if (list.isEmpty()) {
            output.println("Nothing to show");
        } else {
            drawRepetitiveSymbol('-', 63);
            output.printf("|%-10s", "Brand");
            output.printf("|%-9s", "Condition");
            output.printf("|%7s", "Price");
            output.printf("|%6s", "Weigh");
            output.printf("|%-25s|%n", "Description");
            drawRepetitiveSymbol('-', 63);
            for (CoffeePacking packing : list) {
                output.printf("|%-10s", packing.getBrand());
                output.printf("|%-9s", packing.getCondition());
                output.printf("|%7d", packing.getPrice());
                output.printf("|%4dg.", packing.getWeigh());
                output.printf("|%-25s|%n", packing.getDescription());
            }
            drawRepetitiveSymbol('-', 63);
        }
    }

    public static float getCarrying() {
        output.print("Enter carrying capacity in kilo: ");

        String carrying = scanner.next().replaceAll("[^\\d,.]", "");
        carrying = carrying.replaceAll("[,.]", String.valueOf(new DecimalFormatSymbols(Locale.ENGLISH).getDecimalSeparator()));

        return Float.valueOf(carrying);
    }

    public static int getUserChoice() {
        output.print("Make your choice ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        output.println("To use the application you need to create a van with a certain carrying capacity");
        try {

            float carrying = -1;
            while (carrying < 0) {
                carrying = getCarrying();
                if (carrying < 0) {
                    output.print("Carrying capacity can not be negative");
                }
            }

            transporter = new Van(Math.round(carrying * 1000));
            output.println("Van has been created. Carrying capacity is " + transporter.getTransportableWeight() + " gram");
            fillTransporterRandomly();
            output.println("Van free space " + transporter.getFreeWeight() + " gram");

            int userChoice = -1;

            while (userChoice != 0) {
                viewMenu();
                userChoice = getUserChoice();

                switch (userChoice) {
                    case 0:
                        output.close();
                        scanner.close();
                        break;
                    case 1:
                        viewCoffeeList(transporter.getCoffeePacking());
                        break;
                    case 2:
                        transporter.sortByPrice();
                        viewCoffeeList(transporter.getCoffeePacking());
                        break;
                    case 3:
                        output.print("Enter weight ");
                        int search = scanner.nextInt();
                        viewCoffeeList(transporter.findByWeight(search));
                        break;
                    default:
                        output.println("Unknown menu item " + userChoice);

                }
            }

        } catch (NumberFormatException e) {//NOSONAR
            output.print("Unknown number");
        }

    }
}
