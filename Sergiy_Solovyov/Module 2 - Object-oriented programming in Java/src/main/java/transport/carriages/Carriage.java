package transport.carriages;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */

public abstract class Carriage{

    private int passengerCapacity;
    private double luggageWeight;
    private int age;
    private String colour;

    public Carriage(int age, double luggageWeight, int passengerCapacity, String colour) {
        this.age = age;
        this.luggageWeight = luggageWeight;
        this.passengerCapacity = passengerCapacity;
        this.colour = colour;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("age=");
        stringBuilder.append(age);
        stringBuilder.append(", passengerCapacity=");
        stringBuilder.append(passengerCapacity);
        stringBuilder.append(", luggageWeight=");
        stringBuilder.append(String.format("%.3f",luggageWeight)+" tons");
        stringBuilder.append(", colour=");
        stringBuilder.append(colour);
        stringBuilder.append(",");

        return   stringBuilder.toString();
    }

    public String getColour() {
        return colour;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void luggageWeight(double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }


}

