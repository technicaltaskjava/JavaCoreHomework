package RailwayRollingStock.Carriages.Passengers.PassengerCarriageStuff;

import RailwayRollingStock.Carriages.CarriageType;

/**
 * Created by Oleg on 07.03.2016.
 */
public class PassengerServiceFood {

    int numberOfCoffeeServings;
    int numberOfTeaServings;
    int numberOfSnackServings;

    public PassengerServiceFood(CarriageType carriageType) {
        this.numberOfCoffeeServings = carriageType.seatsCount() * 2;
        this.numberOfTeaServings = carriageType.seatsCount() * 2;
        this.numberOfSnackServings = carriageType.seatsCount() * 2;
    }

    public int getNumberOfCoffeeServings() {
        return numberOfCoffeeServings;
    }

    public void setNumberOfCoffeeServings(int numberOfCoffeeServings) {
        this.numberOfCoffeeServings = numberOfCoffeeServings;
    }

    public int getNumberOfTeaServings() {
        return numberOfTeaServings;
    }

    public void setNumberOfTeaServings(int numberOfTeaServings) {
        this.numberOfTeaServings = numberOfTeaServings;
    }

    public int getNumberOfSnackServings() {
        return numberOfSnackServings;
    }

    public void setNumberOfSnackServings(int numberOfSnackServings) {
        this.numberOfSnackServings = numberOfSnackServings;
    }

    public int getCoffeeServing(){
        if( numberOfCoffeeServings == 0){

            return 0;

        }
        numberOfCoffeeServings--;
        return 1;
    }

    public int getTeaServing(){
        if( numberOfTeaServings == 0){
            return 0;
        }
        numberOfTeaServings--;
        return 1;
    }

    public int getSnackServing(){
        if ( numberOfSnackServings == 0)
        {
            return 0;
        }
        numberOfSnackServings--;
        return 1;
    }



}
