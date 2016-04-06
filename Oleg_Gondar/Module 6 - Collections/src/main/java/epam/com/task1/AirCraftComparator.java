package epam.com.task1;

import java.util.Comparator;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class AirCraftComparator implements Comparator<AirCraft> {

    public int compare(AirCraft o1, AirCraft o2) {
        return o1.getFlyDistance().getDistValue() - o2.getFlyDistance().getDistValue();
    }
}
