package RailwayRollingStock.Carriages.Passengers;

import RailwayRollingStock.Carriages.Carriage;
import RailwayRollingStock.Carriages.CarriageType;

/**
 * Created by Oleg on 07.03.2016.
 */
public class CommonPassengerCarriage extends Carriage implements Comparable<CommonPassengerCarriage>, Cloneable{

    protected CarriageType carriageType;

    private int passengersCount;
    private int baggageCount;

    public CommonPassengerCarriage() {
        carriageType = CarriageType.COMMON;
    }

    public CommonPassengerCarriage(CarriageType carriageType){
        this.carriageType = carriageType;
    }

    public CarriageType getCarriageType(){
        return carriageType;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public int getBaggageCount() {
        return baggageCount;
    }

    public void setBaggageCount(int baggageCount) {
        this.baggageCount = baggageCount;
    }
    boolean isSeatsFree(){
        if (passengersCount >= carriageType.seatsCount()){
            return false;
        }
        return true;
    }
    public boolean putPassengerInCarriage(){
        if(isSeatsFree()){
            passengersCount++;
            return true;
        }
        return false;
    }

    public void removePassengerFromCarriage(int passengersCount){
        if (this.passengersCount > passengersCount){
            this.passengersCount -= passengersCount;
        }
        else {
            this.passengersCount = 0;
            System.out.println("ВНИМАНИЕ: Пыталось выйти людей больше, чем было...");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonPassengerCarriage that = (CommonPassengerCarriage) o;

        if (passengersCount != that.passengersCount) return false;
        if (baggageCount != that.baggageCount) return false;
        return carriageType == that.carriageType;

    }

    @Override
    public int hashCode() {
        int result = carriageType.hashCode();
        result = 31 * result + passengersCount;
        result = 31 * result + baggageCount;
        return result;
    }

   @Override
   public int compareTo(CommonPassengerCarriage commonPassengerCarriage) {
       return this.carriageType.seatsCount() - commonPassengerCarriage.getCarriageType().seatsCount();
   }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CommonPassengerCarriage clonedObject = null;
        clonedObject = (CommonPassengerCarriage) super.clone();
        clonedObject.carriageType = CarriageType.COMMON;
        clonedObject.setBaggageCount(this.getBaggageCount());
        clonedObject.setPassengersCount(this.getPassengersCount());
        return clonedObject;
    }
}



