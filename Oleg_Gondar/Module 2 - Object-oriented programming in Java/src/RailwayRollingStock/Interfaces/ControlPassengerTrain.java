package RailwayRollingStock.Interfaces;

import RailwayRollingStock.Carriages.Carriage;
import RailwayRollingStock.Carriages.Passengers.CommonPassengerCarriage;

import java.util.Comparator;

/**
 * Created by Oleg on 07.03.2016.
 */
public interface ControlPassengerTrain {

    void slowDown();

    void startStationStop();

    void endStationStop();


}
