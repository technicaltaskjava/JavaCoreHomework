package com.task1.transport.planes;
/**
 * @author Sergey Solovyov
 */
public class TransportPlane extends AbstractPlane {

    private int quantityCells;

    public TransportPlane(int age, double luggageTotalWeight, int passengerCapacity, String colour, int quantityCells) {
        super(age, luggageTotalWeight, passengerCapacity, colour);
        this.quantityCells = quantityCells;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TransportPlane:\n{");
        stringBuilder.append(super.toString());
        stringBuilder.append(" quantityCells=");
        stringBuilder.append(quantityCells);
        stringBuilder.append("} \n");

        return  stringBuilder.toString();
    }

}
