package RailwayRollingStock.Carriages;

import RailwayRollingStock.Carriages.CarriageType;
import RailwayRollingStock.Carriages.Passengers.CommonPassengerCarriage;
import RailwayRollingStock.ManagementUnits.PassengerTrain;

import java.util.Comparator;

/**
 * Created by Oleg on 08.03.2016.
 */
public class ComparatorForCarridges implements Comparator<CarriageType> {

    CommonPassengerCarriage[] carriages;

    @Override
    public int compare(CarriageType carriageType1, CarriageType carriageType2) {
        return carriageType1.seatsCount() - carriageType2.seatsCount();
    }
}
