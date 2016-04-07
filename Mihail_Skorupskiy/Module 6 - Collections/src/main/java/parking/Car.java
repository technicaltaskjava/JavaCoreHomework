package parking;

public class Car {

    private String name;
    private boolean parked;

    private Car(){}

    public Car(String name){
        this.name = name;
        parked = false;
    }

    public boolean park(Parking parking, int position){
        parked = parking.placeCar(this, position);
        return parked;
    }

    public boolean driveOut(Parking parking){
        parked = parking.removeCar(this);
        return parked;
    }

    public boolean isParked(){
        return parked;
    }

    void setParked(boolean input){
        parked = input;
    }

    @Override
    public String toString(){
        return name;
    }
}
