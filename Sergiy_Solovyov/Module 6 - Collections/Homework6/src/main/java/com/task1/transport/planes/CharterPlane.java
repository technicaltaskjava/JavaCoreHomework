package com.task1.transport.planes;

/**
 * @author Sergey Solovyov
 */
public class CharterPlane extends AbstractPlane {

    private String mainDish;
    private int cookQuantity;

    public CharterPlane(int flightRange, double luggageTotalWeight, int passengerCapacity, String colour, String mainDish, int cookQuantity) {
        super(flightRange, luggageTotalWeight, passengerCapacity, colour);
        this.mainDish = mainDish;
        this.cookQuantity = cookQuantity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CharterPlane:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" cookQuantity=");
        stringBuilder.append(cookQuantity);
        stringBuilder.append(", mainDish=");
        stringBuilder.append(mainDish);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }

    public int getCookerQuantity() {
        return cookQuantity;
    }

    public String getMainDish() {
        return mainDish;
    }

    public void setMainDish(String mainDish) {
        this.mainDish = mainDish;
    }


}
