package taxistation.garage.passengercar;

/**
 * @author Alexey Ushakov
 */
public abstract class PassengerCar implements Comparable<PassengerCar>, Car {
    private int price;
    private int fuelConsumption;

    public abstract String getModel();

    public int getPrice() {
        return price;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public int compareTo(PassengerCar passengerCar) {
        return this.getFuelConsumption() - passengerCar.getFuelConsumption();
    }
}
