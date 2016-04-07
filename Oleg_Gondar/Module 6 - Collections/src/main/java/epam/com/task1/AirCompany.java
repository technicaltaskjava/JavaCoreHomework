package epam.com.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class AirCompany {

    private List<AirCraft> airCrafts;

    public AirCompany() {

        airCrafts = new ArrayList<>();

    }

    public List<AirCraft> findByHoldingCapacity(int f, List<AirCraft> airCrafts) {

        List<AirCraft> temp = new ArrayList<>();

        for (AirCraft a :
                airCrafts) {
            if (a.getHoldingCapacity() == f) {
                temp.add(a);
            }
        }
        return temp.isEmpty() ? null : temp;
    }

    public AirCraft getAircraft(int index) {
        return airCrafts.get(index);
    }

    public void addAirCraft(Distances d, int holdingCapacity, int bearingCapacity) {

        airCrafts.add(new AirCraft(d, holdingCapacity, bearingCapacity));

    }

    public void removeAirCraft(int index) {
        airCrafts.remove(index);
    }

    public void sortAirCrafts() {
        airCrafts.sort(new AirCraftComparator());
    }

    private class AirCraftComparator implements Comparator<AirCraft> {

        @Override
        public int compare(AirCraft o1, AirCraft o2) {
            return o1.getFlyDistance().getDistValue() - o2.getFlyDistance().getDistValue();
        }
    }


}


