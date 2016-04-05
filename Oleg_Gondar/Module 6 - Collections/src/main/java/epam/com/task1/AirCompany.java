package epam.com.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class AirCompany {
    private AirCompany(){
    }

    public static void main(String[] args) {

        List<AirCraft> airCrafts = new ArrayList<AirCraft>();
        fillCollection(airCrafts);
        // showResulst(airCrafts);
        airCrafts.sort(new AirCraftComparator());
        //showResulst(airCrafts);
        try {
            showResulst(findByHoldingCapacity(3, airCrafts));
        }catch (NullPointerException e){
            System.out.println("Nothing found!");
        }


    }
    public static void showResulst(List<AirCraft> airCrafts){

        for (AirCraft a:
                airCrafts) {
            a.showAll();
        }
    }

    public static List<AirCraft> findByHoldingCapacity(double f, List<AirCraft> airCrafts){

        List<AirCraft> temp = new ArrayList<AirCraft>();

        for (AirCraft a:
             airCrafts) {
            if (a.getHoldingCapacity() == f){
                temp.add(a);
            }
        }
        return temp.isEmpty() ? null : temp;
    }

    public static List<AirCraft> fillCollection(List<AirCraft> airCrafts){

        airCrafts.add(new AirCraft(Distances.LONG,3,3));
        airCrafts.add(new AirCraft(Distances.MIDDLE,2,2));
        airCrafts.add(new AirCraft(Distances.SHORT,1,1));
        airCrafts.add(new AirCraft(Distances.LONG,31,31));
        airCrafts.add(new AirCraft(Distances.MIDDLE,21,21));
        airCrafts.add(new AirCraft(Distances.SHORT,11,11));
        airCrafts.add(new AirCraft(Distances.SHORT,3,11));
        return airCrafts;

    }


}


