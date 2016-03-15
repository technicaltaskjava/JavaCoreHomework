package RailwayRollingStock.Interfaces;

import RailwayRollingStock.Carriages.Carriage;
import RailwayRollingStock.ManagementUnits.PassengerTrain;
import RailwayRollingStock.ManagementUnits.Train;

/**
 * Created by Oleg on 08.03.2016.
 */
public interface PassengerTrainStuffCount {

    int getPassengerCount();

    int getBaggageCount();


}
