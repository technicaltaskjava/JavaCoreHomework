package com.task4;

/**
 * @author Sergey Solovyov
 */
public class Car {
    private String carNumber;
    private String ownerName;

    public Car(String carNumber, String ownerName) {
        this.carNumber = carNumber;
        this.ownerName = ownerName;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

       Car car = (Car) obj;

        if (car.getCarNumber() == this.getCarNumber() && car.getOwnerName() == this.getOwnerName())
            return true;

        return false;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }
    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return carNumber.hashCode()*ownerName.hashCode();
    }
}
