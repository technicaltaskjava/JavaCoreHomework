package RailwayRollingStock.ManagementUnits;

import RailwayRollingStock.Carriages.CarriageType;
import RailwayRollingStock.Carriages.ComparatorForCarridges;
import RailwayRollingStock.Carriages.Passengers.*;


/**
 * Created by Oleg on 08.03.2016.
 */
public class RailwayStation {

    public static void main(String[] args) throws CloneNotSupportedException {


        PassengerTrain passengerTrain = makingPassengerTrain();
        metodsDemonstration(passengerTrain);
        ComparatorForCarridges comparatorForCarridges = new ComparatorForCarridges();
        System.out.println("Пример поиска с имплементацией инт. Comparator. \nНайдено " + findCarriagesSomeTypes(CarriageType.HI_SPEED_SECOND_CLASS, passengerTrain, comparatorForCarridges) + " вагонов заданного типа.");
        someCarriagesComparingWithCompareTo(passengerTrain);


    }

    public static void someCarriagesComparingWithCompareTo(PassengerTrain passengerTrain) {

        System.out.println("Сравниваем вагоны 1 и 1 с имплементацией инт. CompareTo \nРезультат:");
        System.out.println((passengerTrain.getCarriages(1).compareTo(passengerTrain.getCarriages(1)) == 0) ? "Equals" : "Not equals");
        System.out.println("Сравниваем вагоны 0 и 1 с имплементацией инт. CompareTo \nРезультат:");
        System.out.println((passengerTrain.getCarriages(0).compareTo(passengerTrain.getCarriages(1)) == 0) ? "Equals" : "Not equals");

    }

    public static int findCarriagesSomeTypes(CarriageType carriageType, PassengerTrain passengerTrain, ComparatorForCarridges comparatorForCarridges) {

        int neededWagonsCount = 0;

        for (CommonPassengerCarriage commonPassengerCarriage : passengerTrain.getCarriages()) {
            if (comparatorForCarridges.compare(commonPassengerCarriage.getCarriageType(), carriageType) == 0) {
                neededWagonsCount++;
            }
        }
        return neededWagonsCount;
    }

    public static void metodsDemonstration(PassengerTrain train) throws CloneNotSupportedException {

        System.out.println("Всего пассажиров в поезде: " + train.getPassengerCount());
        System.out.println("ВСего багажа в поезде: " + train.getBaggageCount());
        train.showAllCarriageTypes();
        System.out.println("Сортировка по типу вагона:");
        train.carriagesSort();
        System.out.println();
        train.showAllCarriageTypes();

    }

    public static PassengerTrain makingPassengerTrain() {
        PassengerTrain train = new PassengerTrain();
        train.addLocomotive();
        train.attachWagon(CarriageType.COMMON);
        train.attachWagon(CarriageType.HI_SPEED_SECOND_CLASS);
        train.attachWagon(CarriageType.HI_SPEED_FIRST_CLASS);
        train.attachWagon(CarriageType.HI_SPEED_SECOND_CLASS);
        train.attachWagon(CarriageType.COMPARTMENT);
        train.carriages[2].setPassengersCount(3);
        train.carriages[1].setPassengersCount(7);
        train.carriages[2].setPassengersCount(11);
        train.carriages[0].setBaggageCount(1);
        train.carriages[1].setBaggageCount(3);
        train.carriages[1].setBaggageCount(343);

        return train;


    }

}










