package javase02.t09.cars;

public class Car {
    private final String carType;
    private final String carClassification;
    private final String carModel;
    private final String carNumber;
    private int carCost;
    private float carFuelConsumption;
    private final int carPeopleCapacity;

    protected Car(String carType,
                 String carClassification,
                 String carModel,
                 String carNumber,
                 int carCost,
                 float carFuelConsumption,
                 int carPeopleCapacity) {
        this.carType = carType;
        this.carClassification = carClassification;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.carCost = carCost;
        this.carFuelConsumption = carFuelConsumption;
        this.carPeopleCapacity = carPeopleCapacity;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarClassification() {
        return carClassification;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getCarCost() {
        return carCost;
    }

    public float getCarFuelConsumption() {
        return carFuelConsumption;
    }

    public int getCarPeopleCapacity() {
        return carPeopleCapacity;
    }

    public void setCarCost(int carCost) {
        this.carCost = carCost;
    }

    public void setCarFuelConsumption(float carFuelConsumption) {
        this.carFuelConsumption = carFuelConsumption;
    }
}
