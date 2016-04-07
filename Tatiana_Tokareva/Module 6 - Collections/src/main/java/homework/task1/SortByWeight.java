package homework.task1;

import java.util.Comparator;

public class SortByWeight implements Comparator<Candies> {
    @Override
    public int compare(Candies o1, Candies o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
