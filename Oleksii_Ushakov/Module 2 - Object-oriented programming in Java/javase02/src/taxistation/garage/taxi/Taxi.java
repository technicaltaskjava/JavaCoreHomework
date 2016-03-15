package taxistation.garage.taxi;

import taxistation.garage.passengercar.Car;

/**
 * @author Alexey Ushakov
 */
public interface Taxi extends Car {

    int getTaxiId();

    void setTaxiId(int taxiId);

    int getPassengerSeatCount();
}
