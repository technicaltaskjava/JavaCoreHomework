package taxistation.garage.taxi;

import taxistation.garage.passengercar.PassengerCar;

/**
 * @author Alexey Ushakov
 */
public class TaxiHatchback extends PassengerCar implements Taxi {
    public static final int MAX_PASSENGER_SEAT_HATCHBACK = 4;
    private static int hatchbackId = 1000;
    private int taxiId;

    public TaxiHatchback() {
        setTaxiId(hatchbackId++);
    }

    @Override
    public String getModel() {
        return "Хэтчбек";
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
        return MAX_PASSENGER_SEAT_HATCHBACK;
    }
}
