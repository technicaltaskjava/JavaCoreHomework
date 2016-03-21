package RailwayRollingStock.Carriages.Passengers;

import RailwayRollingStock.Carriages.CarriageType;
import RailwayRollingStock.Carriages.Passengers.PassengerCarriageStuff.PassengerServiceFood;
import RailwayRollingStock.Carriages.Passengers.PassengerCarriageStuff.PassengerServicesBedclothes;

/**
 * Created by Oleg on 08.03.2016.
 */
public class LongDistancePassengerCarriage extends CommonPassengerCarriage implements Cloneable {

    private final boolean WC = true;
    private PassengerServicesBedclothes passengerServicesBedclothes;
    private PassengerServiceFood passengerServiceFood;

    public LongDistancePassengerCarriage() {
        this.passengerServicesBedclothes = new PassengerServicesBedclothes(carriageType);
        this.passengerServiceFood = new PassengerServiceFood(carriageType);
    }

    public LongDistancePassengerCarriage(CarriageType carriageType){
        super(carriageType);
        this.passengerServicesBedclothes = new PassengerServicesBedclothes(this.carriageType);
        this.passengerServiceFood = new PassengerServiceFood(this.carriageType);

    }

    public PassengerServicesBedclothes getPassengerServicesBedclothes() {
        return passengerServicesBedclothes;
    }

    public void setPassengerServicesBedclothes(PassengerServicesBedclothes passengerServicesBedclothes) {
        this.passengerServicesBedclothes = passengerServicesBedclothes;
    }

    public PassengerServiceFood getPassengerServiceFood() {
        return passengerServiceFood;
    }

    public void setPassengerServiceFood(PassengerServiceFood passengerServiceFood) {
        this.passengerServiceFood = passengerServiceFood;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPassengerServicesBedclothes() != null ? getPassengerServicesBedclothes().hashCode() : 0);
        result = 31 * result + (getPassengerServiceFood() != null ? getPassengerServiceFood().hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LongDistancePassengerCarriage longDistancePassengerCarriage = null;
        longDistancePassengerCarriage = (LongDistancePassengerCarriage) super.clone();
        longDistancePassengerCarriage.carriageType = this.getCarriageType();
        longDistancePassengerCarriage.setPassengersCount(this.getPassengersCount());
        longDistancePassengerCarriage.setBaggageCount(this.getBaggageCount());
        longDistancePassengerCarriage.setPassengerServiceFood(this.getPassengerServiceFood());
        longDistancePassengerCarriage.setPassengerServicesBedclothes(this.getPassengerServicesBedclothes());
        return longDistancePassengerCarriage;
    }
}
