package javase02.t09.cars;

public class TaxiCar extends Car implements Comparable<TaxiCar> {

    public static float kilometerCost;
    private float entranceCost;

    public TaxiCar(String carType,
                  String carClassification,
                  String carModel,
                  String carNumber,
                  int carCost,
                  float carFuelConsumption,
                  int carPeopleCapacity) {
        super(carType, carClassification, carModel, carNumber, carCost, carFuelConsumption, carPeopleCapacity);
    }

    public float countRouteCost(int routeLength) {
        float routeCost = kilometerCost * routeLength + entranceCost;
        return routeCost;
    }

    public float getEntranceCost() {
        return entranceCost;
    }

    public void setEntranceCost(float entranceCost) {
        this.entranceCost = entranceCost;
    }

    public int compareTo(TaxiCar anotherCar) {
        float anotherCarConsumption = anotherCar.getCarFuelConsumption();
        return (int) (this.getCarFuelConsumption() - anotherCarConsumption);
    }

    public void outCarData() {
        System.out.printf("%-20s", this.getCarClassification());
        System.out.printf("%-20s", this.getCarType());
        System.out.printf("%-20s", this.getCarModel());
        System.out.printf("%-20s", this.getCarNumber());
        System.out.printf("%-20s", this.getCarCost());
        System.out.format("%-20.1f", this.getCarFuelConsumption());
        System.out.printf("%-20s%n", this.getCarPeopleCapacity());
    }
}
