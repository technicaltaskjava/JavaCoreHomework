package javase02.t09.operation;

import javase02.t09.carpark.CarPark;
import javase02.t09.cars.TaxiCar;
import javase02.t09.cars.BusinessTaxiCar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CarParkOperation {

    public static void carParkCreate (CarPark totalPark, String iniFile, String iniCostFile) {
        try {
            Scanner scanCostIniFile = new Scanner(new FileInputStream(iniCostFile));

            float kilometerCost = Float.parseFloat(scanCostIniFile.nextLine());
            float entranceCost = Float.parseFloat(scanCostIniFile.nextLine());
            float upCoefficient = Float.parseFloat(scanCostIniFile.nextLine());
            float entranceBusinessCost = Float.parseFloat(scanCostIniFile.nextLine());
            scanCostIniFile.close();

            Scanner scanCreationIniFile = new Scanner(new FileInputStream(iniFile));

            int numberOfCars = Integer.parseInt(scanCreationIniFile.nextLine());
            for (int carIndex = 0; carIndex < numberOfCars; carIndex++) {
                String currentCarClassification = scanCreationIniFile.nextLine();
                String currentCarType = scanCreationIniFile.nextLine();
                String currentCarModel = scanCreationIniFile.nextLine();
                String currentCarNumber = scanCreationIniFile.nextLine();
                int currentCarCost = Integer.parseInt(scanCreationIniFile.nextLine());
                float currentFuelConsumption = Float.parseFloat(scanCreationIniFile.nextLine());
                int currentPeopleCapacity = Integer.parseInt(scanCreationIniFile.nextLine());

                if (Objects.equals(currentCarClassification,"Business")) {
                    BusinessTaxiCar currentBusinessCar = new BusinessTaxiCar(currentCarType,
                                                                            currentCarClassification,
                                                                            currentCarModel,
                                                                            currentCarNumber,
                                                                            currentCarCost,
                                                                            currentFuelConsumption,
                                                                            currentPeopleCapacity);
                    currentBusinessCar.setEntranceCost(entranceBusinessCost);
                    currentBusinessCar.upCoefficient = upCoefficient;
                    totalPark.addBusinessCarToPark(currentBusinessCar);
                }
                else {
                    TaxiCar currentCar = new TaxiCar(currentCarType,
                                                    currentCarClassification,
                                                    currentCarModel,
                                                    currentCarNumber,
                                                    currentCarCost,
                                                    currentFuelConsumption,
                                                    currentPeopleCapacity);
                    currentCar.setEntranceCost(entranceCost);
                    currentCar.kilometerCost = kilometerCost;
                    totalPark.addCarToPark(currentCar);
                }
            }
            scanCreationIniFile.close();
            System.out.print(iniFile + " : Park of Taxi successfully created. ");
            System.out.println(String.valueOf(totalPark.getNumberOfCars()) + " cars added.");
            System.out.println(iniCostFile + " : Cost data added.");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
    }

    public static float countRouteCost (CarPark totalPark, String iniFile) {
        try {
            Scanner scanIniFile = new Scanner(new FileInputStream(iniFile));
            int routeDistance = Integer.parseInt(scanIniFile.nextLine());
            scanIniFile.close();
            return totalPark.countRouteCost(routeDistance);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found...");
            return 0;
        }
    }

    public static float countBusinessRouteCost (CarPark totalPark, String iniFile) {
        try {
            Scanner scanIniFile = new Scanner(new FileInputStream(iniFile));
            int routeDistance = Integer.parseInt( scanIniFile.nextLine() );
            scanIniFile.close();
            return totalPark.countBusinessRouteCost(routeDistance);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found...");
            return 0;
        }
    }

    /*
    Ini-file findCar.ini contents desired values for search for each field of Taxi-Car object.
    There are seven fields at TaxiCar class.
    If the field is numeric at findCar.ini we define two values - beginning and end of desired range.
    If the field is String we defined desired value as String variable or type "any" for any value of field.
    During reading from ini-file we create two objects of TaxiCar class which determine lower and upper ends
    of field ranges for each object. Comparing each field of TaxiCar object at method CompareCarsByFields with
    that lower and upper ends (equals or "any" for String fields) we can fully determine accordance of the current car
    to desired arguments range.
     */
    public static TaxiCar[] findTheCar (CarPark totalPark, String iniFile) {
        try {
            Scanner scanIniFile = new Scanner(new FileInputStream(iniFile));

            String currentCarClassification = scanIniFile.nextLine();
            String currentCarType = scanIniFile.nextLine();
            String currentCarModel = scanIniFile.nextLine();
            String currentCarNumber = scanIniFile.nextLine();
            int currentLowerCarCost = scanIniFile.nextInt();
            int currentUpperCarCost = scanIniFile.nextInt();
            float currentLowerFuelConsumption = scanIniFile.nextFloat();
            float currentUpperFuelConsumption = scanIniFile.nextFloat();
            int currentLowerPeopleCapacity = scanIniFile.nextInt();
            int currentUpperPeopleCapacity = scanIniFile.nextInt();
            scanIniFile.close();

            TaxiCar lowerLimit = new TaxiCar(currentCarType,
                                            currentCarClassification,
                                            currentCarModel,
                                            currentCarNumber,
                                            currentLowerCarCost,
                                            currentLowerFuelConsumption,
                                            currentLowerPeopleCapacity);
            TaxiCar upperLimit = new TaxiCar(currentCarType,
                                            currentCarClassification,
                                            currentCarModel,
                                            currentCarNumber,
                                            currentUpperCarCost,
                                            currentUpperFuelConsumption,
                                            currentUpperPeopleCapacity);
            return totalPark.findCarByParameter(lowerLimit, upperLimit);
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        CarPark totalPark = new CarPark();
        carParkCreate(totalPark, "createPark.ini", "createCostParam.ini");

        System.out.println();
        System.out.println("routeDistance.ini : Counting the cost of route... ");
        System.out.print("Total cost of route is " + Float.toString(countRouteCost(totalPark, "routeDistance.ini")));
        System.out.print(" by Standart Car and ");
        System.out.println(Float.toString(countBusinessRouteCost(totalPark, "routeDistance.ini")) + " by Business Class.");

        System.out.println();
        System.out.println("Total cost of all cars from park is : " + Integer.toString(totalPark.countParkCost(totalPark)));

        TaxiCar[] temporaryPark = new TaxiCar[totalPark.getNumberOfCars()];
        temporaryPark = totalPark.sortCarsByConsumption();
        System.out.format("%n%s%n", "Sorting total park by fuel consumption:");
        System.out.printf("%-20s", "Car class");
        System.out.printf("%-20s", "Car type");
        System.out.printf("%-20s", "Car model");
        System.out.printf("%-20s", "Car number");
        System.out.printf("%-20s", "Car cost");
        System.out.format("%-20s", "Fuel consumption");
        System.out.printf("%-20s%n", "Car people capacity");
        for (int indexOfArray = 0; indexOfArray < temporaryPark.length; indexOfArray++) {
            temporaryPark[indexOfArray].outCarData();
        }

        System.out.println();
        System.out.println("findCar.ini : Searching car with those arguments... ");
        temporaryPark = findTheCar(totalPark, "findCar.ini");
        if (temporaryPark != null) {
            for (int indexOfArray = 0; indexOfArray < temporaryPark.length; indexOfArray++) {
                temporaryPark[indexOfArray].outCarData();
            }
        }
    }

}