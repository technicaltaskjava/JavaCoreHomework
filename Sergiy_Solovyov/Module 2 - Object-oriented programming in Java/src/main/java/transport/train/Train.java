package transport.train;
import transport.carriages.*;
import transport.comporators.AgeComparator;

import java.util.Arrays;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 07.03.2016
 */
public class Train {

    private Carriage [] train;

    public Train(){
        this.train = new Carriage[20];
    }

    public boolean addCarriage(Carriage carriage){
        for (int i = 0; i < train.length; i++){
            if (train[i] == null){ train[i] = carriage;
            return true;}
        }
        train = Arrays.copyOf(train, train.length * 2);
        for (int i = train.length/2; i < train.length; i++){
            if (train[i] == null) train[i] = carriage;
            return true;
        }
        return false;
    }
    public int trainPassengersCapacity(){
        int passengers = 0;
        for (Carriage carriage: train){
            if (carriage != null)
                passengers += carriage.getPassengerCapacity();
        }
        System.out.println("Train may accommodate "+ passengers + " passengers." +"\n");
        return passengers;
    }
    public double trainLuggageWeight(){
        double totalWeight = 0;
        for (Carriage carriage: train){
            if (carriage != null)
                totalWeight += carriage.getLuggageWeight();
        }
        System.out.print("Train may transport ");
        System.out.printf("%.3f", totalWeight);
        System.out.println( " tons of luggage." +"\n");
        return totalWeight;
    }

public String findCarriagesByColour(String colour){
    Carriage [] carriages = new Carriage[train.length];
    int count = 0;
    for (int i = 0; i < train.length; i++) {
        Carriage iterCar = null;
        if (train[i] != null) {
            iterCar = train[i];
            if (iterCar.getColour().equalsIgnoreCase(colour)){
                carriages[i] = iterCar;
                count++;}
        }
    }
        if ( count == 0)
        return "********There are not any carriage with colour: " + colour + ".********\n";

        StringBuilder stringBuilder = new StringBuilder();
        for (Carriage carriage: carriages){
            if (carriage != null)
                stringBuilder.append(carriage.toString());
        }

    return stringBuilder.toString();
}

    public void sortByAge(){
        Arrays.sort(train, new AgeComparator());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Train:" + "\n");
        for (Carriage carriage: train){
            if (carriage != null)
                stringBuilder.append(carriage.toString());
        }
        return stringBuilder.toString();
    }




}
