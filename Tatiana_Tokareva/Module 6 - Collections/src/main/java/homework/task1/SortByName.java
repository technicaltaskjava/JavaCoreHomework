package homework.task1;

import java.util.Comparator;

public class SortByName implements Comparator<Candies> {
    @Override
    public int compare(Candies o1, Candies o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
