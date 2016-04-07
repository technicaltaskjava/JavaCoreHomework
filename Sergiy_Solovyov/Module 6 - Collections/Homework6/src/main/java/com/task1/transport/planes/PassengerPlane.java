package com.task1.transport.planes;
/**
 * @author Sergey Solovyov
 */
public class PassengerPlane extends AbstractPlane {

    private int hostessQuantity;

    public PassengerPlane(int age, double luggageTotalWeight, int passengerCapacity, String colour, int hostessQuantity) {
        super(age, luggageTotalWeight, passengerCapacity, colour);
        this.hostessQuantity = hostessQuantity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PassengerPlane:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" hostessQuantity=");
        stringBuilder.append(hostessQuantity);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }
}