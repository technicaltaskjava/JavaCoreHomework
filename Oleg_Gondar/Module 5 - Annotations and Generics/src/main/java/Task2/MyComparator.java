package Task2;

import java.util.Comparator;

/**
 * Created by Oleg on 29.03.2016.
 */
public class MyComparator <T extends Comparable<T>> implements Comparator<T>{

    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

}
