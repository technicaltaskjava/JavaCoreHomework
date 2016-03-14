package RailwayRollingStock.Locomotives;

import RailwayRollingStock.Interfaces.ControlPassengerTrain;

/**
 * Created by Oleg on 07.03.2016.
 */
public class PassengerLocomotive extends Locomotive implements ControlPassengerTrain {

    @Override
    public void startMoving() {
        System.out.println("Locomotive is moving train!");

    }

    @Override
    public void increaseSpeed() {
        System.out.println("Locomotive is increased train spped!");

    }

    @Override
    public void decreaseSpeed() {
        System.out.println("Locomotive is decreased train spped!");

    }

    @Override
    public void slowDown() {
        System.out.println("Locomotive is slowly decreased train spped!");

    }

    @Override
    public void stop() {

        System.out.println("Locomotive is stoped!");

    }

    @Override
    public void startStationStop() {

    }

    @Override
    public void endStationStop() {

    }

}
