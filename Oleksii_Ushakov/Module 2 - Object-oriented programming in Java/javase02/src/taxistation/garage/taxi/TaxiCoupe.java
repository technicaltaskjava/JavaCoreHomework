package taxistation.garage.taxi;

import taxistation.garage.passengercar.PassengerCar;

/**
 * @author Alexey Ushakov
 */
public class TaxiCoupe extends PassengerCar implements Taxi {
    public static final int MAX_PASSENGER_SEAT_COUPE = 1;
    private static int coupeId = 10;
    private int taxiId;

    public TaxiCoupe() {
        setTaxiId(coupeId++);
    }

    @Override
    public String getModel() {
        return "Купе";
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
        return MAX_PASSENGER_SEAT_COUPE;
    }

}
