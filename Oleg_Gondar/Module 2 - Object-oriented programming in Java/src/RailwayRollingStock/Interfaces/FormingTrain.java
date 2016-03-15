package RailwayRollingStock.Interfaces;

import RailwayRollingStock.Carriages.CarriageType;

/**
 * Created by Oleg on 07.03.2016.
 */
public interface FormingTrain {

    public void addLocomotive();

    public void attachWagon(CarriageType carriageType);

    public void detachWagon();

}
