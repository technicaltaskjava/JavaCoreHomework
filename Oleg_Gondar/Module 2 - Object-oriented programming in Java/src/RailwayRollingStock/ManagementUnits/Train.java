package RailwayRollingStock.ManagementUnits;

import RailwayRollingStock.Carriages.Carriage;
import RailwayRollingStock.Carriages.Passengers.CommonPassengerCarriage;
import RailwayRollingStock.Interfaces.FormingTrain;
import RailwayRollingStock.Locomotives.Locomotive;

/**
 * Created by Oleg on 08.03.2016.
 */
public abstract class Train implements FormingTrain{

    private int carriageNumbers;
    protected Locomotive locomotive;

    public int getCarriageNumbers() {
        return carriageNumbers;
    }

    public void setCarriageNumbers(int carriageNumbers) {
        this.carriageNumbers = carriageNumbers;
    }
}
