package javase02.t09.carpark;

import javase02.t09.cars.BusinessTaxiCar;
import javase02.t09.cars.TaxiCar;
import java.util.Arrays;

public class CarPark {

    private int numberOfCars;
    private TaxiCar[] standartPark;
    private BusinessTaxiCar[] businessPark;

    public void setNumberOfCars(Integer numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public Integer getNumberOfCars() {
        return numberOfCars;
    }

    public CarPark() {
        this.standartPark = new TaxiCar[0];
        this.businessPark =  new BusinessTaxiCar[0];
    }

    public CarPark(TaxiCar[] standartPark, BusinessTaxiCar[] businessPark) {
        System.arraycopy(this.standartPark, 0, standartPark, 0, standartPark.length);
        System.arraycopy(this.businessPark, 0, businessPark, 0, businessPark.length);
    }

    public void addCarToPark (TaxiCar currentCar) {
        TaxiCar[] tempStandartPark = new TaxiCar[this.standartPark.length+1];
        System.arraycopy(this.standartPark, 0, tempStandartPark, 0, this.standartPark.length);
        tempStandartPark [tempStandartPark.length-1] = currentCar;
        this.standartPark = new TaxiCar[tempStandartPark.length];
        System.arraycopy(tempStandartPark, 0, this.standartPark, 0, this.standartPark.length);
        this.numberOfCars++;
    }

    public void addBusinessCarToPark (BusinessTaxiCar currentCar) {
        TaxiCar[] tempBusinessPark = new BusinessTaxiCar [this.businessPark.length+1];
        System.arraycopy(this.businessPark, 0, tempBusinessPark, 0, this.businessPark.length);
        tempBusinessPark[tempBusinessPark.length-1] = currentCar;
        this.businessPark = new BusinessTaxiCar [tempBusinessPark.length];
        System.arraycopy(tempBusinessPark, 0, this.businessPark, 0, this.businessPark.length);
        this.numberOfCars++;
    }

    public void deleteCarFromPark (String carNumber) {
        for(int indexOfArray = 0; indexOfArray < this.standartPark.length; indexOfArray++) {
            if (this.standartPark[indexOfArray].getCarNumber().equals(carNumber)) {
                TaxiCar[] tempStandartPark = new TaxiCar[this.standartPark.length-1];
                System.arraycopy(this.standartPark, 0, tempStandartPark, 0, indexOfArray);
                System.arraycopy(this.standartPark, indexOfArray+1, tempStandartPark, indexOfArray,
                        this.standartPark.length-indexOfArray-1);
                this.standartPark = new TaxiCar[tempStandartPark.length];
                System.arraycopy(tempStandartPark, 0, this.standartPark, 0, this.standartPark.length);
                this.numberOfCars--;
                System.out.println("Car with number " + carNumber + " has been deleted from standart park.");
                System.out.println("Total amount of cars is " + String.valueOf(this.numberOfCars) + " now.");
            }
        }
        for(int indexOfArray = 0; indexOfArray < this.businessPark.length; indexOfArray++) {
            if (this.businessPark[indexOfArray].getCarNumber().equals(carNumber)) {
                TaxiCar[] tempBusinessPark = new TaxiCar[this.businessPark.length-1];
                System.arraycopy(this.businessPark, 0, tempBusinessPark, 0, indexOfArray);
                System.arraycopy(this.businessPark, indexOfArray+1, tempBusinessPark, indexOfArray,
                        this.businessPark.length-indexOfArray-1);
                this.businessPark = new BusinessTaxiCar[tempBusinessPark.length];
                System.arraycopy(tempBusinessPark, 0, this.businessPark, 0, this.businessPark.length);
                this.numberOfCars--;
                System.out.println("Car with number " + carNumber + " has been deleted from business park.");
                System.out.println("Total amount of cars is " + String.valueOf(this.numberOfCars) + " now.");
            }
        }
    }

    public int countTotalParkCost() {
        int totalParkCost = 0;
        for (int carIndex = 0; carIndex < this.standartPark.length; carIndex++){
            totalParkCost += this.standartPark[carIndex].getCarCost();
        }
        for (int carIndex = 0; carIndex < this.businessPark.length; carIndex++){
            totalParkCost += this.businessPark[carIndex].getCarCost();
        }
        return totalParkCost;
    }

    public TaxiCar[] sortCarsByConsumption () {
        TaxiCar[] temporaryPark = new TaxiCar [ this.standartPark.length+this.businessPark.length ];
        System.arraycopy( this.standartPark, 0, temporaryPark, 0, this.standartPark.length );
        System.arraycopy( this.businessPark, 0, temporaryPark, this.standartPark.length, this.businessPark.length );
        Arrays.sort( temporaryPark );
        return temporaryPark;
    }

    public float countRouteCost (int routeDistance) {
        return this.standartPark[0].countRouteCost(routeDistance);
    }

    public float countBusinessRouteCost (int routeDistance) {
        return this.businessPark[0].countRouteCost(routeDistance);
    }

    public int countParkCost (CarPark totalPark) {
        return totalPark.countTotalParkCost();
    }

    /* read the comments at CarParkOperation.java */
    public boolean CompareCarsByFields (TaxiCar[] temporaryPark, TaxiCar lowerLimit, TaxiCar upperLimit, int indexOfArray) {
        int totalAmountOfFields=7;
        int counterOfFilteredFields = 0;
        if (temporaryPark[indexOfArray].getCarType().equals(lowerLimit.getCarType()) || (lowerLimit.getCarType().equals("any"))) {
            counterOfFilteredFields++;
        }
        if (temporaryPark[indexOfArray].getCarClassification().equals(lowerLimit.getCarClassification())
                || (lowerLimit.getCarClassification().equals("any"))) {
            counterOfFilteredFields++;
        }
        if (temporaryPark[indexOfArray].getCarModel().equals(lowerLimit.getCarModel())
                || (lowerLimit.getCarModel().equals("any"))) {
            counterOfFilteredFields++;
        }
        if (temporaryPark[indexOfArray].getCarNumber().equals(lowerLimit.getCarNumber())
                || (lowerLimit.getCarNumber().equals("any"))) {
            counterOfFilteredFields++;
        }
        if ((temporaryPark[indexOfArray].getCarCost() >= lowerLimit.getCarCost())
                && (temporaryPark[indexOfArray].getCarCost() <= upperLimit.getCarCost())) {
            counterOfFilteredFields++;
        }
        if ((temporaryPark[indexOfArray].getCarFuelConsumption() >= lowerLimit.getCarFuelConsumption())
                && (temporaryPark[indexOfArray].getCarFuelConsumption() <= upperLimit.getCarFuelConsumption())) {
            counterOfFilteredFields++;
        }
        if ((temporaryPark[indexOfArray].getCarPeopleCapacity() >= lowerLimit.getCarPeopleCapacity())
                && (temporaryPark[indexOfArray].getCarPeopleCapacity() <= upperLimit.getCarPeopleCapacity())) {
            counterOfFilteredFields++;
        }
        if (counterOfFilteredFields == totalAmountOfFields) {
            return true;
        }
        else {
            return false;
        }
    }

    /* read the comments at CarParkOperation.java */
    public TaxiCar[] findCarByParameter (TaxiCar lowerLimit, TaxiCar upperLimit) {
        TaxiCar[] temporaryPark = new TaxiCar[this.standartPark.length+this.businessPark.length];
        TaxiCar[] foundedPark = new TaxiCar[0];
        System.arraycopy(this.standartPark, 0, temporaryPark, 0, this.standartPark.length);
        System.arraycopy(this.businessPark, 0, temporaryPark, this.standartPark.length, this.businessPark.length);
        boolean carFounded = false;
        for (int indexOfArray = 0; indexOfArray < temporaryPark.length; indexOfArray++) {
            if (CompareCarsByFields(temporaryPark, lowerLimit, upperLimit, indexOfArray) == true) {
                carFounded = true;
                TaxiCar[] tempFoundedPark = new TaxiCar[foundedPark.length+1];
                System.arraycopy(foundedPark, 0, tempFoundedPark, 0, foundedPark.length);
                tempFoundedPark [tempFoundedPark.length-1] = temporaryPark[indexOfArray];
                foundedPark = new TaxiCar[tempFoundedPark.length];
                System.arraycopy(tempFoundedPark, 0, foundedPark, 0, foundedPark.length);
            }
        }
        if (carFounded == true) {
            return foundedPark;
        }
        else {
            System.out.println("No any matches...");
            return null;
        }
    }
}

