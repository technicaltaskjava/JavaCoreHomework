package javase02.t09.cars;

public class BusinessTaxiCar extends TaxiCar {
    public static float upCoefficient;
    private float entranceCost;

    public BusinessTaxiCar(String carType,
                          String carClassification,
                          String carModel,
                          String carNumber,
                          int carCost,
                          float carFuelConsumption,
                          int carPeopleCapacity) {
        super(carType, carClassification, carModel, carNumber, carCost, carFuelConsumption, carPeopleCapacity);
    }

    public float countRouteCost(int routeLength) {
        return TaxiCar.kilometerCost * routeLength * upCoefficient + entranceCost;
    }

    public float getEntranceCost() {
        return entranceCost;
    }

    public void setEntranceCost(float entranceCost) {
        this.entranceCost = entranceCost;
    }
}
