package transport.train.traingeneration.trainfromfile;

import transport.carriages.Carriage;
import transport.carriages.DiningCar;
import transport.carriages.LuggageVan;
import transport.carriages.SleepingCar;

import java.util.Arrays;


/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class CarriageFact {

    public static Carriage createCarriage (String currentLine, String fileName){
        Carriage carriage = null;
        String [] arr = currentLine.split(" ");

        if (fileName.equals("DiningCars"))
            carriage =new DiningCar(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3], arr[4],Integer.valueOf(arr[5]));
        if (fileName.equals("LuggageVans"))
            carriage = new LuggageVan(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3], Integer.valueOf(arr[4]));
        if (fileName.equals("SleepingCars"))
            carriage = new SleepingCar(Integer.valueOf(arr[0]),Double.valueOf(arr[1]),
                    Integer.valueOf(arr[2]),arr[3],Integer.valueOf(arr[4]));
        return carriage;
    }
}
