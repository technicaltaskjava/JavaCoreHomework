package task2;

import java.util.Comparator;

public class ComparatorUser implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        String o1Name = o1.getName();
        String o2Name = o2.getName();
        return o1Name.compareTo(o2Name);
    }
}
