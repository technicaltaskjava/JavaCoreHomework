package com.task1.transport.airplanecomp;
import com.task1.transport.planes.AbstractPlane;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Sergey Solovyov
 */
public class AirCompany {

    private ArrayList<AbstractPlane> planes = new ArrayList<>();;

    public boolean addPlane(AbstractPlane plane){
        return planes.add(plane);
    }

    public int planePassengersCapacity(){
        int passengers = 0;
        for (AbstractPlane plane: planes){
            if (plane != null)
                passengers += plane.getPassengerCapacity();
        }
        System.out.println("Planes may accommodate "+ passengers + " passengers." +"\n");
        return passengers;
    }
    public double  planeLuggageWeight(){
        double totalWeight = 0;
        for (AbstractPlane plane: planes){
            if (plane != null)
                totalWeight += plane.getLuggageWeight();
        }
        System.out.print("Planes may transport ");
        System.out.printf("%.3f", totalWeight);
        System.out.println( " tons of luggage." +"\n");
        return totalWeight;
    }

public String findPlaneByColour(String colour){
    ArrayList<AbstractPlane> pls = new ArrayList<>();
    for (int i = 0; i < planes.size(); i++) {
        if (planes.get(i) != null) {
            AbstractPlane iterPlane = planes.get(i);
            if (iterPlane.getColour().equalsIgnoreCase(colour)){
                pls.add(iterPlane);
                }
        }
    }
        if ( pls.isEmpty()){
        return "********There are not any planes with colour: " + colour + ".********\n";}

        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractPlane plane: pls){
            if (plane != null)
                stringBuilder.append(plane.toString());
        }

    return stringBuilder.toString();
}

    public void sortByFlightRange(){
        Collections.sort(planes, new Comparator<AbstractPlane>() {
            @Override
            public int compare(AbstractPlane o1, AbstractPlane o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.getFlightRange() - o2.getFlightRange();
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Plane:" + "\n");
        for (AbstractPlane plane: planes){
            if (plane != null)
                stringBuilder.append(plane.toString());
        }
        return stringBuilder.toString();
    }

}
