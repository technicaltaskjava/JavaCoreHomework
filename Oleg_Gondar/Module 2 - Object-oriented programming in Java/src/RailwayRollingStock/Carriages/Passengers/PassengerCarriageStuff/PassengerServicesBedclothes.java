package RailwayRollingStock.Carriages.Passengers.PassengerCarriageStuff;

import RailwayRollingStock.Carriages.CarriageType;

/**
 * Created by Oleg on 07.03.2016.
 */
public class PassengerServicesBedclothes {

    private int newBedclothesCount;
    private int usedBedclothescount;

    public PassengerServicesBedclothes(CarriageType carriageType) {
        this.newBedclothesCount = carriageType.seatsCount();
    }

    public int getUsedBedclothescount() {
        return usedBedclothescount;
    }

    public void setUsedBedclothescount(int usedBedclothescount) {
        this.usedBedclothescount = usedBedclothescount;
    }

    public int getNewBedclothesCount() {

        return newBedclothesCount;
    }

    public void setNewBedclothesCount(int newBedclothesCount) {
        this.newBedclothesCount = newBedclothesCount;
    }

}
