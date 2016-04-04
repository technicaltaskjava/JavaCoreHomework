package task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] array = new String[]{"cat", "dog", "mouse", "ghost", "ant", "butterfly"};
        System.out.println(String.format("Comparable array: %s", Arrays.toString(array)));
        String max = CompareUtils.max(array);
        System.out.println(String.format("Max=%s", max));
        String min = CompareUtils.min(array);
        System.out.println(String.format("Min=%s", min));
        String median = CompareUtils.median(array);
        System.out.println(String.format("Median=%s", median));
        User[] users = new User[]{new User("Bobi"), new User("Jone"), new User("Shara"), new User("Billy")};
        System.out.println(String.format("Comparator array: %s", Arrays.toString(users)));

        ComparatorUser comparator = new ComparatorUser();
        User maxUser = CompareUtils.max(users, comparator);
        System.out.println(String.format("Max=%s", maxUser));
        User minUser = CompareUtils.min(users, comparator);
        System.out.println(String.format("Min=%s", minUser));
        User medianUser = CompareUtils.median(users, comparator);
        System.out.println(String.format("Median=%s", medianUser));
    }

}
