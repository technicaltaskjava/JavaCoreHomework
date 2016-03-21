package taxistation.garage.taxi;

import taxistation.garage.passengercar.PassengerCar;

/**
 * @author Alexey Ushakov
 */
public class TaxiSedan extends PassengerCar implements Taxi {
    public static final int MAX_PASSENGER_SEAT_SEDAN = 4;
    private static int sedanId = 100;
    private int taxiId;

    public TaxiSedan() {
        setTaxiId(sedanId++);
    }

    @Override
    public String getModel() {
        return "Седан";
    }

    @Override
    public int getTaxiId() {
        return taxiId;
    }

    @Override
    public void setTaxiId(int taxiId) {
        this.taxiId = taxiId;
    }

    @Override
    public int getPassengerSeatCount() {
        return MAX_PASSENGER_SEAT_SEDAN;
    }
}
