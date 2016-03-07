import taxistation.garage.taxi.Taxi;
import taxistation.TaxiStation;

import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */

public class MainMenu {
    private final static String TABLE_FORMAT = "%1$-";

    public static void main(String[] args) {
        TaxiStation taxiStation = new TaxiStation();
        Scanner scanner = new Scanner(System.in);
        int userChoose = -1;

        printMenu();
        while (userChoose != 0) {
            System.out.print("Сделайте свой выбор > ");
            userChoose = scanner.nextInt();
            switch (userChoose) {
                case 1:
                    printCarTable(taxiStation.getCarAll());
                    break;
                case 2:
                    System.out.println("Стоимость автопарка: " + taxiStation.getCarParkCost());
                    break;
                case 3:
                    taxiStation.sortByFuelConsumption();
                    System.out.println("Отсортировано");
                    printCarTable(taxiStation.getCarAll());
                    break;
                case 4:
                    Taxi[] searchResult = taxiStation.searchTaxiByPrice(getPriceToSearch());
                    if (searchResult.length == 0) {
                        System.out.println("Такого автомобиля не найдено");
                    } else {
                        printCarTable(searchResult);
                    }
                    break;
                default:
                    if (userChoose != 0) {
                        System.err.println("Пункт меню " + userChoose + " не обнаружен");
                    }
            }
            printMenu();
        }
    }

    private static void printMenu() {
        System.out.println("+---+--------------------------------+");
        System.out.println("| 1 | Отобразить автопарк            |");
        System.out.println("| 2 | Отобразить стоимость автопарка |");
        System.out.println("| 3 | Сортировать по расходу топлива |");
        System.out.println("| 4 | Найти автомобиль по цене       |");
        System.out.println("+---+--------------------------------+");
        System.out.println("| 0 | Выход                          |");
        System.out.println("+---+--------------------------------+");
    }

    private static void printCarTable(Taxi[] cars) {


        System.out.println("+-----------+------+----------------+-------+----------------+");
        System.out.println("|   Модель  | Код  | Макс.пасажиров | Цена  | Расход топлива |");
        System.out.println("+-----------+------+----------------+-------+----------------+");
        for (Taxi car : cars) {
            System.out.print("| " + getFormatString(car.getModel(), 10));
            System.out.print("| " + getFormatString(car.getTaxiId(), 5));
            System.out.print("|      " + getFormatString(car.getPassengerSeatCount(), 10));
            System.out.print("| " + getFormatString(car.getPrice(), 6));
            System.out.print("|      " + getFormatString(car.getFuelConsumption(), 10));
            System.out.println("|");
        }
        System.out.println("+-----------+------+----------------+-------+----------------+");
    }

    private static String getFormatString(String line, int length) {
        return String.format(TABLE_FORMAT + length + "s", line);
    }

    private static String getFormatString(int number, int length) {
        return String.format(TABLE_FORMAT + length + "s", number);
    }

    private static int getPriceToSearch() {
        System.out.print("Введите цену искомого автомобиля > ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
