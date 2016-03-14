package RailwayRollingStock.Locomotives;

/**
 * Created by Oleg on 07.03.2016.
 */
public abstract class Locomotive {

    public abstract void startMoving();

    public abstract void increaseSpeed();

    public abstract void decreaseSpeed();

    public abstract void slowDown();

    public abstract void stop();

}
