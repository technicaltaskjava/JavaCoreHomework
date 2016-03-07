package transport.train.traingeneration;

import transport.carriages.DiningCar;
import transport.carriages.LuggageVan;
import transport.carriages.SleepingCar;
import transport.train.Train;

/**
 * Created by Lammi on 07.03.2016.
 */
public class TrainGenerator {

    public static Train generateTrain(int quantityDiningCars, int quantityLuggageVans, int quantitySleepingCars){
        Train train = new Train();

        while (quantityDiningCars > 0){
               train.addCarriage(CarriageFactory.createCarriage("DiningCar"));
               quantityDiningCars--;}

        while (quantityLuggageVans > 0){
            train.addCarriage(CarriageFactory.createCarriage("LuggageVan"));
            quantityLuggageVans--;}

        while (quantitySleepingCars > 0){
            train.addCarriage(CarriageFactory.createCarriage("SleepingCar"));
            quantitySleepingCars--;}

        return train;
    }



}
