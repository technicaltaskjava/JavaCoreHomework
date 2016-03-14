package taxistation.garage.passengercar;

/**
 * @author Alexey Ushakov
 */
public interface Car {
    String getModel();

    int getPrice();

    int getFuelConsumption();

    void setPrice(int price);

    void setFuelConsumption(int fuelConsumption);
}
