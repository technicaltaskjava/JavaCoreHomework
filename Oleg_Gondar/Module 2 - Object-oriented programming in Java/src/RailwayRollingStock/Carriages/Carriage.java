package RailwayRollingStock.Carriages;

/**
 * Created by Oleg on 07.03.2016.
 */

public abstract class Carriage implements Cloneable {

    private static int numberOfCarriage;

    public int getNumberOfCarriage() {
        return numberOfCarriage;
    }

    public void setNumberOfCarriage(int numberOfCarriage) {
        this.numberOfCarriage = numberOfCarriage;
    }


}
