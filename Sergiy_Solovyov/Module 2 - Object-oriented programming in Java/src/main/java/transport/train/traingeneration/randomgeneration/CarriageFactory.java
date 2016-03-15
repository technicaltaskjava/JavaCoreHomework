package transport.train.traingeneration.randomgeneration;

import transport.carriages.Carriage;
import transport.carriages.DiningCar;
import transport.carriages.LuggageVan;
import transport.carriages.SleepingCar;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class CarriageFactory {

    private static String [] colours = {"red", "blue", "white"};
    private static String [] mainDishes = {"bread", "milk", "fish"};
    private static Random random = new Random();



    public static Carriage createCarriage (String carr){
        Carriage carriage = null;
        if (carr.equals("DiningCar"))

            carriage = new DiningCar(random.nextInt(50)+50,random.nextDouble()*50,
                           random.nextInt(20)+30, colours[random.nextInt(colours.length)],
                           mainDishes[random.nextInt(mainDishes.length)],random.nextInt(5)+2);

        if (carr.equals("LuggageVan"))

            carriage = new LuggageVan(random.nextInt(50)+50,random.nextDouble()*2000,
                           random.nextInt(10)+10, colours[random.nextInt(2)], random.nextInt(200)+200);

        if (carr.equals("SleepingCar"))

            carriage = new SleepingCar(random.nextInt(50)+50,random.nextDouble()*50,
                           random.nextInt(100)+100, colours[random.nextInt(2)], random.nextInt(2)+2);

        return carriage;

    }
}
