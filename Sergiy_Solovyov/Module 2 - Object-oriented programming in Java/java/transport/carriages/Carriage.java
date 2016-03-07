package transport.carriages;

public abstract class Carriage{

    private int passengerCapacity;
    private double luggageWeightCarriage;
    private int age;
    private String colour;

    public Carriage(int age, double luggageWeightCarriage, int passengerCapacity, String colour) {
        this.age = age;
        this.luggageWeightCarriage = luggageWeightCarriage;
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
        stringBuilder.append(luggageWeightCarriage);
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

    public double getLuggageWeightCarriage() {
        return luggageWeightCarriage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void luggageWeightCarriage(double luggageTotalWeight) {
        this.luggageWeightCarriage = luggageWeightCarriage;
    }


}

